package com.hp.dit.beetbook.Controller.dailyactivity;

import com.hp.dit.beetbook.form.dailyactivity.DailyactivityForm;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.modals.UsersDetials;
import com.hp.dit.beetbook.modals.information.InformationMarkers;
import com.hp.dit.beetbook.repositories.information.InformationRepository;
import com.hp.dit.beetbook.repositories.user.UserRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DailyActivityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InformationRepository informationRepository;

    @RequestMapping(value = "/dailyActivity", method = RequestMethod.GET)
    public String dailyActivity(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                model.addAttribute("usersDetails", user);
                model.addAttribute("dailyactivityForm", new DailyactivityForm());
                return "dailyActivity";
            }



        }
    }

    //getDailyActivityList
    @Transactional
    @RequestMapping(value = "/getDailyActivityList", method = RequestMethod.POST)
    public String getDailyActivityList(@ModelAttribute("dailyactivityForm") DailyactivityForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            try {
                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);
                System.out.println(form.toString());
                List<InformationMarkers> data = informationRepository.getUploadedInformationByOfficialDateWise(Integer.parseInt(form.getUserId()),form.getDate());
                System.out.println(data);

               // model.addAttribute("dailyactivityForm", new DailyactivityForm());
                model.addAttribute("usersDetails", user);
                model.addAttribute("data", data);
                model.addAttribute("beatId", form.getBeatId());
                model.addAttribute("sosdpoId", form.getSosdpoId());
                model.addAttribute("userId", form.getUserId());
                model.addAttribute("psId", form.getPsId());
                form.setDate(form.getDate());


                return "dailyActivity";
            } catch (Exception ex) {
                return "dailyActivity";
            }

        }
    }




}
