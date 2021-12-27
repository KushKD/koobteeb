package com.hp.dit.police.inventory.Controller;


import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.entities.UnitsEntity;
import com.hp.dit.police.inventory.form.state.StateForm;
import com.hp.dit.police.inventory.form.state.UpdateState;
import com.hp.dit.police.inventory.form.units.UnitForm;
import com.hp.dit.police.inventory.form.units.UpdateUnit;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.units.UnitsRepository;
import com.hp.dit.police.inventory.validators.StateValidator;
import com.hp.dit.police.inventory.validators.StateValidatorUpdate;
import com.hp.dit.police.inventory.validators.unitValidator.UnitValidator;
import com.hp.dit.police.inventory.validators.unitValidator.UnitValidatorUpdate;
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
public class UnitsController {

    @Autowired
    UnitValidator unitValidator;

    @Autowired
    UnitValidatorUpdate unitValidatorUpdate;

    @Autowired
    UnitsRepository unitsRepository;

    @RequestMapping(value = "/createUnit", method = RequestMethod.GET)
          public String createUnit(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("unitForm", new UnitForm());
                return "createUnit";
            }



        }
          }


          //saveState
          @Transactional
          @RequestMapping(value = "/saveUnit", method = RequestMethod.POST)
          public String saveUnit(@ModelAttribute("unitForm") UnitForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
              unitValidator.validate(form, bindingResult);
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
              if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                  return "login";
              } else {
                  UnitsEntity savedUnit = null;
                  if (bindingResult.hasErrors()) {
                      return "createUnit";
                  }

                  try {
                      UnitsEntity unit = new UnitsEntity();
                      unit.setUnitName(form.getUnitName().toString());
                      unit.setUnitDesc(form.getUnitDescription().toString());
                      unit.setActive(true);
                      unit.setDeleted(false);
                      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                      Date date = new Date(timestamp.getTime());
                      unit.setCreatedDate(date);
                      savedUnit = unitsRepository.save(unit);
                      form.setUnitDescription("");
                      form.setUnitName("");
                      request.getSession().setAttribute("successMessage", "Unit Saved Successfully. Generated Unit Id is:- " + unit.getUnitId());

                      return "createUnit";
                  } catch (Exception ex) {
                      request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                      return "createUnit";
                  }
              }


          }


    @RequestMapping(value = "/viewUnits", method = RequestMethod.GET)
    public String viewUnit(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                List<UnitsEntity> units = unitsRepository.getAllUnits();
                model.addAttribute("units", units);
                return "viewUnits";
            }



        }
    }


    @RequestMapping(value = "/updateUnit/{unit_id}", method = RequestMethod.GET)
    public String updateState(@PathVariable("unit_id")Integer unit_id, Model model,HttpServletRequest request) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                return "login";
            } else {

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                if(user==null){
                    return "login";
                }else{

                    UnitsEntity unit = unitsRepository.getUnitViaUnitId(unit_id);
                    System.out.println(unit.toString());
                    model.addAttribute("unit_to_update", unit);
                    model.addAttribute("updateUnit", new UpdateUnit());
                    return "updateUnit";
                }



            }

    }


    //saveState
    @Transactional
    @RequestMapping(value = "/updateUnitEntity", method = RequestMethod.POST)
    public String updateStateEntry(@ModelAttribute("updateUnit") UpdateUnit form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        unitValidatorUpdate.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            UnitsEntity savedUnit = null;
            if (bindingResult.hasErrors()) {
                return "createUnit";
            }

            try {

                //Get State Data via ID

                UnitsEntity unit = new UnitsEntity();

                unit = unitsRepository.getUnitViaUnitId(Integer.parseInt(form.getUnitId()));

                unit.setUnitName(form.getUnitName().toString());
                unit.setUnitDesc(form.getUnitDescription().toString());

                if (form.getUnitIsActive().equalsIgnoreCase("T")) {
                    unit.setActive(true);
                } else {
                    unit.setActive(false);
                }

                if (form.getUnitIsDeleted().equalsIgnoreCase("T")) {
                    unit.setDeleted(true);
                } else {
                    unit.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                unit.setCreatedDate(date);
                savedUnit = unitsRepository.save(unit);
                form.setUnitName("");
                form.setUnitDescription("");
                request.getSession().setAttribute("successMessage", "Unit Updated.");

                return "redirect:/viewUnits";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createUnit";
            }

        }
    }
}
