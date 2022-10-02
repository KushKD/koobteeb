package com.hp.dit.election_ems.Controller.bank;


import com.hp.dit.election_ems.entities.BankMaster;
import com.hp.dit.election_ems.form.sosdpo.BankForm;
import com.hp.dit.election_ems.form.sosdpo.BankUpdate;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.bank.BankRepository;
import com.hp.dit.election_ems.validators.SoSdpoValidator;
import com.hp.dit.election_ems.validators.SoSdpoValidatorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class BankController {



    @Autowired
    BankRepository bankRepository;

    @Autowired
    SoSdpoValidator soSdpoValidator;

    @Autowired
    SoSdpoValidatorUpdate soSdpoValidatorUpdate;

    @RequestMapping(value = "/createsoSdpo", method = RequestMethod.GET)
          public String createSoSDPO(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                model.addAttribute("bankForm", new BankForm());
                return "createSoSdpo";
            }



        }
          }


          //saveState
          @Transactional
          @RequestMapping(value = "/savesosdpo", method = RequestMethod.POST)
          public String saveSoSDPO(@ModelAttribute("bankForm") BankForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
              soSdpoValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  BankMaster savedState = null;
                  if (bindingResult.hasErrors()) {
                      return "createSoSdpo";
                  }

                  try {
                      BankMaster sodpo = new BankMaster();
                      sodpo.setBankName(form.getSoSdpo().toString());
                      sodpo.setActive(true);
                      sodpo.setDeleted(false);
                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      sodpo.setCreatedDate(date);
                      savedState = bankRepository.save(sodpo);
                      form.setSoSdpo("");
                      request.getSession().setAttribute("successMessage", "Bank Saved Successfully. Generated Bank Id is:- " + savedState.getBankId());

                      return "createSoSdpo";
                  } catch (Exception ex) {
                      request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                      return "createSoSdpo";
                  }
              }


          }


    @RequestMapping(value = "/viewsoSdpo", method = RequestMethod.GET)
    public String viewSoSDPO(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                return "viewSoSdpo";
            }



        }
    }


    @RequestMapping(value = "/updateSoSdpo/{sosdpoId}", method = RequestMethod.GET)
    public String updateSoSDPO(@PathVariable("sosdpoId")Integer id, Model model,HttpServletRequest request) throws Exception {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                return "login";
            } else {

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                if(user==null){
                    return "login";
                }else{

                    BankMaster so_sdpo = bankRepository.getAllSOSdpoViaId(id);
                    System.out.println(so_sdpo.toString());
                    model.addAttribute("sosdpo_to_update", so_sdpo);
                    model.addAttribute("bankUpdate", new BankUpdate());
                    return "updateSoSdpo";
                }





            }

    }


    //saveState
    @Transactional
    @RequestMapping(value = "/updateSoSDPOEntity", method = RequestMethod.POST)
    public String updateSoSDPOEntity(@ModelAttribute("bankUpdate") BankUpdate form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        soSdpoValidatorUpdate.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            BankMaster savedState = null;
            if (bindingResult.hasErrors()) {
                return "createSoSdpo";
            }

            try {

                //Get State Data via ID

                BankMaster updateSoSdpo = new BankMaster();

                updateSoSdpo = bankRepository.getAllSOSdpoViaId(Integer.parseInt(form.getSoSdpoId()));

                updateSoSdpo.setBankName(form.getSoSdpoName().toString());
                updateSoSdpo.setBankId(Integer.parseInt(form.getSoSdpoId()));

                if (form.getSoSdpoActive().equalsIgnoreCase("T")) {
                    updateSoSdpo.setActive(true);
                } else {
                    updateSoSdpo.setActive(false);
                }

                if (form.getSoSdpoDeleted().equalsIgnoreCase("T")) {
                    updateSoSdpo.setDeleted(true);
                } else {
                    updateSoSdpo.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                updateSoSdpo.setUpdatedOn(date);
                savedState = bankRepository.save(updateSoSdpo);
                form.setSoSdpoName("");
                request.getSession().setAttribute("successMessage", "So/SDPO Updated.");

                return "redirect:/viewsoSdpo";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createSoSdpo";
            }

        }
    }
}
