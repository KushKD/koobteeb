package com.hp.dit.election_ems.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        System.out.println("This is Login Controller");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }



    @RequestMapping(value =  "/logout" , method = RequestMethod.GET)
    public String logout(Model model,HttpServletRequest request) {
        System.out.println("Logout Successful");
      //  model.addAttribute("login", new Lo());
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "login";
    }
}
