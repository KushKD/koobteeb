package com.hp.dit.police.inventory.Controller;


import com.hp.dit.police.inventory.entities.PoliceLines;
import com.hp.dit.police.inventory.form.sosdpo.SoSdpoForm;
import com.hp.dit.police.inventory.form.sosdpo.SoSdpoUpdate;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.policelines.PoliceLinesRepository;
import com.hp.dit.police.inventory.validators.PoliceLinesValidator;
import com.hp.dit.police.inventory.validators.PoliceLinesValidatorUpdate;
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
public class PoliceLineController {



    @Autowired
    PoliceLinesRepository policeLinesRepository;

    @Autowired
    PoliceLinesValidator policeLinesValidator;

    @Autowired
    PoliceLinesValidatorUpdate policeLinesValidatorUpdate;

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
              policeLinesValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  PoliceLines savedState = null;
                  if (bindingResult.hasErrors()) {
                      return "createSoSdpo";
                  }

                  try {
                      PoliceLines sodpo = new PoliceLines();
                      sodpo.setPolicelineName(form.getSoSdpo().toString());
                      sodpo.setActive(true);
                      sodpo.setDeleted(false);
                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      sodpo.setCreatedDate(date);
                      savedState = policeLinesRepository.save(sodpo);
                      form.setSoSdpo("");
                      request.getSession().setAttribute("successMessage", "SO SDPO Saved Successfully. Generated SO SDPO Id is:- " + savedState.getPolicelineId());

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

                List<PoliceLines> sosdpoList = policeLinesRepository.getAllSOSdpo();
                model.addAttribute("sosdpo", sosdpoList);
                return "viewSoSdpo";
            }



        }
    }


    @RequestMapping(value = "/updateSoSdpo/{policelineId}", method = RequestMethod.GET)
    public String updateSoSDPO(@PathVariable("policelineId")Integer id, Model model,HttpServletRequest request) throws Exception {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                return "login";
            } else {

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                if(user==null){
                    return "login";
                }else{

                    PoliceLines so_sdpo = policeLinesRepository.getAllSOSdpoViaId(id);
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
        policeLinesValidatorUpdate.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            PoliceLines savedState = null;
            if (bindingResult.hasErrors()) {
                return "createSoSdpo";
            }

            try {

                //Get State Data via ID

                PoliceLines updateSoSdpo = new PoliceLines();

                updateSoSdpo = policeLinesRepository.getAllSOSdpoViaId(Integer.parseInt(form.getPolicelineId()));

                updateSoSdpo.setPolicelineName(form.getPolicelineName().toString());
                updateSoSdpo.setPolicelineId(Integer.parseInt(form.getPolicelineId()));

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
                savedState = policeLinesRepository.save(updateSoSdpo);
                form.setPolicelineId("");
                request.getSession().setAttribute("successMessage", "So/SDPO Updated.");

                return "redirect:/viewsoSdpo";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createSoSdpo";
            }

        }
    }
}
