package com.hp.dit.beetbook.Controller;

import com.google.gson.JsonObject;
import com.hp.dit.beetbook.CustomLogin.CustomUserService;
import com.hp.dit.beetbook.CustomLogin.SecurityService;
import com.hp.dit.beetbook.entities.RolesEntity;
import com.hp.dit.beetbook.entities.UserEntity;
import com.hp.dit.beetbook.form.RegisterUser;
import com.hp.dit.beetbook.form.RolesForm;
import com.hp.dit.beetbook.form.SearchID;
import com.hp.dit.beetbook.form.showIdCardList.showIdCardList;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.repositories.user.UserRepository;
import com.hp.dit.beetbook.services.RoleService;
import com.hp.dit.beetbook.services.UserService;
import com.hp.dit.beetbook.validators.GenerateIdCardValidator;
import com.hp.dit.beetbook.validators.RoleValidator;
import com.hp.dit.beetbook.validators.SearchIdCardValidator;
import com.hp.dit.beetbook.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
            //Save the Object in Session
            request.getSession().setAttribute("UserData", data.get(0));

         //   if(authority_.equalsIgnoreCase("REVENUE")  || authority_.equalsIgnoreCase("CASHIER")){
           //     return "redirect:/twoStepVerification";
           // }else{
                return "homepage_new";
           // }
           // return "redirect:/twoStepVerification";


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
            //Get the User Data and Set Set the Data in Session
            List<LoggedInUserSession> data = userRepository.getUserGeoData(username);
            //Save the Object in Session
            request.getSession().setAttribute("UserData", data.get(0));

            //   if(authority_.equalsIgnoreCase("REVENUE")  || authority_.equalsIgnoreCase("CASHIER")){
            //     return "redirect:/twoStepVerification";
            // }else{
            return "homepage_new";
            // }


        }
    }












    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("We are here");

        return "login";
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("registerUser", new RegisterUser());
            return "createuser";
        }


    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    @Transactional
    public String saveUser(@ModelAttribute("registerUser") RegisterUser registerUser, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(registerUser, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            if (bindingResult.hasErrors()) {
                return "createuser";
            }
            try {
                UserEntity user = new UserEntity();
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setActive(true);
                user.setMobileNumber(Long.valueOf(registerUser.getMobileNumber()));
                user.setUserName(registerUser.getUsername());
                user.setFirstName(registerUser.getFirstName());
                user.setLastName(registerUser.getLastName());
                user.setStateId(Integer.parseInt(registerUser.getStateId()));
                user.setDistrictId(Integer.parseInt(registerUser.getDistrictId()));
              //  user.setBarrierId(Integer.parseInt(registerUser.getBarrierId()));
                user.setPassword(encoder.encode(registerUser.getPassword()));
                String roleIid = registerUser.getRoleId();

                Optional<RolesEntity> role = roleService.getRoleDetails(roleIid);
                if (role.get() != null) {
                    List<RolesEntity> list = new ArrayList<RolesEntity>();
                    list.add(role.get());
                    user.setRoles(list);
                    UserEntity savedData = userservice.saveUser(user);

                    request.getSession().setAttribute("successMessage", savedData.getUserName() + "  Successfully Saved. ID is" + savedData.getUserId());
                    registerUser.setMobileNumber("");
                    registerUser.setPasswordConfirm("");
                    registerUser.setPassword("");
                    registerUser.setUsername("");
                    registerUser.setRoleId("0");
                    return "createuser";
                } else {
                    registerUser.setMobileNumber("");
                    registerUser.setPasswordConfirm("");
                    registerUser.setPassword("");
                    registerUser.setUsername("");
                    registerUser.setRoleId("0");
                    model.addAttribute("serverError", "No Role Name and Role Description Exist with this ID");
                    return "createuser";
                }

            } catch (Exception ex) {
                registerUser.setMobileNumber("");
                registerUser.setPasswordConfirm("");
                registerUser.setUsername("");
                registerUser.setPassword("");
                model.addAttribute("serverError", ex.toString());
                return "createuser";
            }
        }

    }


    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("rolesForm", new RolesForm());
            return "createrole";
        }
    }


    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("rolesForm") RolesForm roleForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            roleValidator.validate(roleForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "createrole";
            }
            try {
                RolesEntity rolesEntity = new RolesEntity();
                rolesEntity.setActive(true);
                rolesEntity.setRoleName(roleForm.getRoleName());
                rolesEntity.setRoleDescription(roleForm.getRoleDescription());
                RolesEntity savedData = roleService.saveRole(rolesEntity);
                roleForm.setRoleName("");
                roleForm.setRoleDescription("");
                request.getSession().setAttribute("successMessage", savedData.getRoleName() + " role Successfully Saved. ID is" + savedData.getRoleId());
                return "createrole";
            } catch (Exception ex) {
                roleForm.setRoleName("");
                roleForm.setRoleDescription("");
                model.addAttribute("serverError", ex.toString());
                return "createrole";
            }
        }
    }

    @RequestMapping(value = "/showIdCards", method = RequestMethod.GET)
    public String showIdCardList(Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            request.getSession().setAttribute("successMessage", "");

            String authority_ = null;

            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            for (GrantedAuthority authority : authorities) {
                authority_ = authority.getAuthority().toString();
                System.out.println(authority.getAuthority().toString());
            }

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user.toString());

            if (user != null) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.now();
                System.out.println(dtf.format(localDate).toString());

                showIdCardList date = new showIdCardList();
                    model.addAttribute("showIdCardList", date);
                    date.setDate(dtf.format(localDate).toString());
                    model.addAttribute("stateId", user.getStateId());
                    model.addAttribute("districtId", user.getDistrictId());
                   // model.addAttribute("barrierId", user.getBarrierId());


                    return "showidcards";


            }else{
                return "errorPage";
            }


        }
    }

    //approvedCardList   approvedCards
    @RequestMapping(value = "/approvedCardList", method = RequestMethod.GET)
    public String approvedCardList(@ModelAttribute("showIdCardList") showIdCardList idcard, BindingResult bindingResult, Model model, HttpServletRequest request) {
        // generateIdCardValidator.validate(idcard, bindingResult);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            request.getSession().setAttribute("successMessage", "");

            String authority_ = null;

            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            for (GrantedAuthority authority : authorities) {
                authority_ = authority.getAuthority().toString();
                System.out.println(authority.getAuthority().toString());
            }

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user.toString());

            if (user != null) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.now();
                System.out.println(dtf.format(localDate).toString());

                showIdCardList date = new showIdCardList();
                model.addAttribute("showIdCardList", date);
                date.setDate(dtf.format(localDate).toString());
                model.addAttribute("stateId", user.getStateId());
                model.addAttribute("districtId", user.getDistrictId());
               // model.addAttribute("barrierId", user.getBarrierId());


                return "approvedCards";


            }else{
                return "errorPage";
            }


        }

    }






    @RequestMapping(value = "/searchId", method = RequestMethod.GET)
    public String searchIdCard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("searchId", new SearchID());
            return "searchid";
        }
    }


}
