package com.hp.dit.beetbook.Controller;


import com.hp.dit.beetbook.entities.PinMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.form.sosdpo.SoSdpoForm;
import com.hp.dit.beetbook.form.sosdpo.SoSdpoUpdate;
import com.hp.dit.beetbook.form.state.StateForm;
import com.hp.dit.beetbook.form.state.UpdateState;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.repositories.sosdpo.SoSdpoRepository;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import com.hp.dit.beetbook.validators.SoSdpoValidator;
import com.hp.dit.beetbook.validators.SoSdpoValidatorUpdate;
import com.hp.dit.beetbook.validators.StateValidator;
import com.hp.dit.beetbook.validators.StateValidatorUpdate;
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
import java.util.List;

@Controller
public class SoSdpoController {



    @Autowired
    SoSdpoRepository soSdpoRepository;

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

                model.addAttribute("soSdpoForm", new SoSdpoForm());
                return "createSoSdpo";
            }



        }
          }


          //saveState
          @Transactional
          @RequestMapping(value = "/savesosdpo", method = RequestMethod.POST)
          public String saveSoSDPO(@ModelAttribute("soSdpoForm") SoSdpoForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
              soSdpoValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  S0SdpoMaster savedState = null;
                  if (bindingResult.hasErrors()) {
                      return "createSoSdpo";
                  }

                  try {
                      S0SdpoMaster sodpo = new S0SdpoMaster();
                      sodpo.setSosdpoName(form.getSoSdpo().toString());
                      sodpo.setActive(true);
                      sodpo.setDeleted(false);
                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      sodpo.setCreatedDate(date);
                      savedState = soSdpoRepository.save(sodpo);
                      form.setSoSdpo("");
                      request.getSession().setAttribute("successMessage", "SO SDPO Saved Successfully. Generated SO SDPO Id is:- " + savedState.getSosdpoId());

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

                List<S0SdpoMaster> sosdpoList = soSdpoRepository.getAllSOSdpo();
                model.addAttribute("sosdpo", sosdpoList);
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

                    S0SdpoMaster so_sdpo = soSdpoRepository.getAllSOSdpoViaId(id);
                    System.out.println(so_sdpo.toString());
                    model.addAttribute("sosdpo_to_update", so_sdpo);
                    model.addAttribute("soSdpoUpdate", new SoSdpoUpdate());
                    return "updateSoSdpo";
                }





            }

    }


    //saveState
    @Transactional
    @RequestMapping(value = "/updateSoSDPOEntity", method = RequestMethod.POST)
    public String updateSoSDPOEntity(@ModelAttribute("soSdpoUpdate") SoSdpoUpdate form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        soSdpoValidatorUpdate.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            S0SdpoMaster savedState = null;
            if (bindingResult.hasErrors()) {
                return "createSoSdpo";
            }

            try {

                //Get State Data via ID

                S0SdpoMaster updateSoSdpo = new S0SdpoMaster();

                updateSoSdpo = soSdpoRepository.getAllSOSdpoViaId(Integer.parseInt(form.getSoSdpoId()));

                updateSoSdpo.setSosdpoName(form.getSoSdpoName().toString());
                updateSoSdpo.setSosdpoId(Integer.parseInt(form.getSoSdpoId()));

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
                savedState = soSdpoRepository.save(updateSoSdpo);
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
