package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.form.activebeat.ActiveBeat;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.modals.activeBeatModal.ActiveBeatModal;
import com.hp.dit.police.inventory.repositories.userlocationlogs.UserLocationLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActiveBeatController {

    @Autowired
    UserLocationLogsRepository userLocationLogsRepository;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/activeBeat", method = RequestMethod.GET)
    public String saveRole(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {


            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{


                model.addAttribute("activeBeat", new ActiveBeat());
                return "activebeat";
            }

        }
    }

    @RequestMapping(value = "/getActiveBeat", method = RequestMethod.POST)
    public String getActiveBeat(@ModelAttribute("activeBeat") ActiveBeat activeBeatForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            if (bindingResult.hasErrors()) {
                return "activebeat";
            }
            try {

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                if(user==null){
                    return "login";
                }else{
                    List<Object[]> data = userLocationLogsRepository.getActiveBeats(activeBeatForm.getToDate());
                    System.out.println(data.toString());

                    if (!data.isEmpty()) {
                        List<ActiveBeatModal> activeBeatModal = new ArrayList<>();


                        for (Object[] result : data) {
                            ActiveBeatModal pojo = new ActiveBeatModal();
                            pojo.setBeatId((Integer) result[0]);
                            pojo.setUsername((String) result[1]);
                            pojo.setUserId((Integer) result[2]);
                            pojo.setRoleId((Integer) result[3]);
                            pojo.setMobile((BigInteger) result[4]);
                            pojo.setBeatName((String) result[5]);
                            pojo.setPoliceStationName((String) result[6]);
                            pojo.setDate((String) result[7]);
                            activeBeatModal.add(pojo);
                        }
                        model.addAttribute("activeBeats", activeBeatModal);
                        activeBeatForm.setToDate(activeBeatForm.getToDate());
                        return "activebeat";
                    } else {
                        model.addAttribute("serverError", "No Data Found");
                        return "activebeat";
                    }
                }

            } catch (Exception ex) {
                model.addAttribute("serverError", ex.toString());
                return "activebeat";
            }
        }

    }
}
