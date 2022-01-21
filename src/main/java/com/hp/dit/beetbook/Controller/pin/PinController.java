package com.hp.dit.beetbook.Controller.pin;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.PinMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.form.module.ModuleForm;
import com.hp.dit.beetbook.form.pin.PinForm;
import com.hp.dit.beetbook.form.state.UpdateState;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.repositories.pin.PinRepository;
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
public class PinController {

    @Autowired
    PinRepository pinRepository;

    @RequestMapping(value = "/viewpin", method = RequestMethod.GET)
    public String viewStates(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                return "viewpin";
            }



        }
    }


    @RequestMapping(value = "/updatePin/{state_id}", method = RequestMethod.GET)
    public String updatePin(@PathVariable("state_id")Integer state_id, Model model,HttpServletRequest request) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                PinMaster pinData = pinRepository.getPinViaId(state_id);
                System.out.println(pinData.toString());
                model.addAttribute("pin_to_update", pinData);
                model.addAttribute("pinForm", new PinForm());
                return "updatepin";
            }





        }

    }

    @Transactional
    @RequestMapping(value = "/updatePinEntity", method = RequestMethod.POST)
    public String updatePinEntity(@ModelAttribute("pinForm") PinForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
      //  updateState.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            PinMaster pinMaster = null;
            if (bindingResult.hasErrors()) {
                return "updatepin";
            }

            try {

                //Get State Data via ID

                PinMaster pin = new PinMaster();

                pin = pinRepository.getPinViaId(Integer.parseInt(form.getPinId()));

                pin.setPin(Integer.parseInt(form.getPin()));

                StatesMaster statesMaster = new StatesMaster();
                statesMaster.setStateID(Integer.parseInt(form.getStateId()));
                pin.setStateID(statesMaster);

                DistrictMaster districtMaster = new DistrictMaster();
                districtMaster.setDistrictId(Integer.parseInt(form.getDistrictId()));
                pin.setDistrictId(districtMaster);


                if (form.getStateIsActive().equalsIgnoreCase("T")) {
                    pin.setActive(true);
                } else {
                    pin.setActive(false);
                }

                if (form.getStateIsDeleted().equalsIgnoreCase("T")) {
                    pin.setDeleted(true);
                } else {
                    pin.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                pin.setUpdatedOn(date);
                pinMaster = pinRepository.save(pin);
                //form.set("");
                request.getSession().setAttribute("successMessage", "Pin Updated.");

                return "redirect:/viewpin";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "updatepin";
            }

        }
    }
}
