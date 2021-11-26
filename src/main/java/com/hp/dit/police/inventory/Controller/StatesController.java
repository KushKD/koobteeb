package com.hp.dit.police.inventory.Controller;


import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.form.state.StateForm;
import com.hp.dit.police.inventory.form.state.UpdateState;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.validators.StateValidator;
import com.hp.dit.police.inventory.validators.StateValidatorUpdate;
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
public class StatesController {

    @Autowired
    StateValidator stateValidator;

    @Autowired
    StateValidatorUpdate updateState;

    @Autowired
    StateRepository stateRepository;

    @RequestMapping(value = "/createState", method = RequestMethod.GET)
          public String createState(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("stateForm", new StateForm());
                return "createState";
            }



        }
          }


          //saveState
          @Transactional
          @RequestMapping(value = "/saveState", method = RequestMethod.POST)
          public String saveState(@ModelAttribute("stateForm") StateForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
              stateValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  StatesMaster savedState = null;
                  if (bindingResult.hasErrors()) {
                      return "createState";
                  }

                  try {
                      StatesMaster state = new StatesMaster();
                      state.setStateName(form.getStateName().toString());
                      state.setActive(true);
                      state.setDeleted(false);
                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      state.setCreatedDate(date);
                      savedState = stateRepository.save(state);
                      form.setStateName("");
                      request.getSession().setAttribute("successMessage", "State Saved Successfully. Generated State Id is:- " + savedState.getStateID());

                      return "createState";
                  } catch (Exception ex) {
                      request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                      return "createState";
                  }
              }


          }


    @RequestMapping(value = "/viewStates", method = RequestMethod.GET)
    public String viewStates(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                List<StatesMaster> states = stateRepository.getAllStates();
                model.addAttribute("states", states);
                return "viewStates";
            }



        }
    }


    @RequestMapping(value = "/updateState/{state_id}", method = RequestMethod.GET)
    public String updateState(@PathVariable("state_id")Integer state_id, Model model,HttpServletRequest request) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                return "login";
            } else {

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                if(user==null){
                    return "login";
                }else{

                    StatesMaster state = stateRepository.getStateViaStateId(state_id);
                    System.out.println(state.toString());
                    model.addAttribute("state_to_update", state);
                    model.addAttribute("updateState", new UpdateState());
                    return "updateState";
                }



            }

    }


    //saveState
    @Transactional
    @RequestMapping(value = "/updateStateEntity", method = RequestMethod.POST)
    public String updateStateEntry(@ModelAttribute("updateState") UpdateState form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        updateState.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            StatesMaster savedState = null;
            if (bindingResult.hasErrors()) {
                return "createState";
            }

            try {

                //Get State Data via ID

                StatesMaster state = new StatesMaster();

                state = stateRepository.getStateViaStateId(Integer.parseInt(form.getStateId()));

                state.setStateName(form.getStateName().toString());
                state.setStateID(Integer.parseInt(form.getStateId()));

                if (form.getStateIsActive().equalsIgnoreCase("T")) {
                    state.setActive(true);
                } else {
                    state.setActive(false);
                }

                if (form.getStateIsDeleted().equalsIgnoreCase("T")) {
                    state.setDeleted(true);
                } else {
                    state.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                state.setUpdatedOn(date);
                savedState = stateRepository.save(state);
                form.setStateName("");
                request.getSession().setAttribute("successMessage", "State Updated.");

                return "redirect:/viewStates";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createState";
            }

        }
    }
}
