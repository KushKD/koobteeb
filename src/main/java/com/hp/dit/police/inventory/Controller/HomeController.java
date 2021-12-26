package com.hp.dit.police.inventory.Controller;

import com.google.gson.JsonObject;
import com.hp.dit.police.inventory.CustomLogin.CustomUserService;
import com.hp.dit.police.inventory.CustomLogin.SecurityService;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.user.UserRepository;
import com.hp.dit.police.inventory.services.RoleService;
import com.hp.dit.police.inventory.services.UserService;
import com.hp.dit.police.inventory.validators.RoleValidator;
import com.hp.dit.police.inventory.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private GenerateIdCardValidator generateIdCardValidator;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private UserService userservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SearchIdCardValidator searchIdCardValidator;



    @Autowired
    private ServletContext context;


    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage(Model model, HttpServletRequest request) {

        String authority_ = null;
        JsonObject object = new JsonObject();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            for (GrantedAuthority authority : authorities) {
                authority_ = authority.getAuthority().toString();
                System.out.println(authority.getAuthority().toString());
            }
            //Get the User Data and Set Set the Data in Session
            List<LoggedInUserSession> data = userRepository.getUserGeoData(username);
            request.getSession().setAttribute("UserData", data.get(0));


                return "redirect:/dashboard";


        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest request) {

        String authority_ = null;
        JsonObject object = new JsonObject();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            for (GrantedAuthority authority : authorities) {
                authority_ = authority.getAuthority().toString();
                System.out.println(authority.getAuthority().toString());
            }

           LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "homepage_new";
            }






        }
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("We are here");

        return "login";
    }



}
