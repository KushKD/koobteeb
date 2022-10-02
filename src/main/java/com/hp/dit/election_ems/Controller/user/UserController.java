package com.hp.dit.election_ems.Controller.user;

import com.hp.dit.election_ems.CustomLogin.CustomUserService;
import com.hp.dit.election_ems.entities.RolesEntity;
import com.hp.dit.election_ems.entities.UserEntity;
import com.hp.dit.election_ems.form.RolesForm;
import com.hp.dit.election_ems.form.user.RegisterUser;
import com.hp.dit.election_ems.form.user.UpdateUser;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.modals.UsersDetials;
import com.hp.dit.election_ems.repositories.user.UserRepository;
import com.hp.dit.election_ems.services.RoleService;
import com.hp.dit.election_ems.services.UserService;
import com.hp.dit.election_ems.validators.RoleValidator;
import com.hp.dit.election_ems.validators.UpdateUserValidator;
import com.hp.dit.election_ems.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private UserService userservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UpdateUserValidator updateUserValidator;





    @RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
    public String viewBarrier(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "viewUser";
            }




        }
    }

    //updateUser
    @RequestMapping(value = "/updateUser/{user_id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("user_id")Integer user_id, Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{


                List<Object[]> users = userRepository.getSpecificUserViaUserRoleId(user_id);
                List<UsersDetials> usersDetails = new ArrayList<>();

                for (Object[] result : users) {
                    UsersDetials pojo = new UsersDetials();
                    pojo.setUser_id((Integer) result[0]);
                    pojo.setFirst_name((String) result[1]);
                    pojo.setLast_name((String) result[2]);
                    pojo.setUsername((String) result[3]);
                    pojo.setRole_id((Integer) result[4]);
                    pojo.setRole_name((String) result[5]);
                    pojo.setMobile_number((BigInteger) result[6]);
                    pojo.setState_id((Integer) result[7]);
                    pojo.setState_name((String) result[8]);
                    pojo.setDistrict_id((Integer) result[9]);
                    pojo.setDistrict_name((String) result[10]);
                    pojo.setBeat_id((Integer) result[11]);
                    pojo.setBeat_name((String) result[12]);
                    pojo.setRank((String) result[13]);
                    pojo.setSodpo_id((Integer) result[14]);
                    pojo.setPs_id((Integer) result[15]);

                    usersDetails.add(pojo);
                }

                model.addAttribute("usersDetails", usersDetails.get(0));
                model.addAttribute("userRoleIdOld", usersDetails.get(0).getRole_id());
                model.addAttribute("updateUser", new UpdateUser());
                System.out.println(usersDetails.get(0).toString());
                return "updateUser";
            }


        }
    }


    @RequestMapping(value = "/updateSelectedUser", method = RequestMethod.POST)
    @Transactional
    public String saveUser(@ModelAttribute("updateUser") UpdateUser updateUser, BindingResult bindingResult, Model model, HttpServletRequest request) {
        updateUserValidator.validate(updateUser, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            if (bindingResult.hasErrors()) {
                return "updateUser";
            }
            try {
                UserEntity user = new UserEntity();
                PasswordEncoder encoder = new BCryptPasswordEncoder();

                if(updateUser.getUserIsActive().equalsIgnoreCase("T")){
                    user.setActive(true);
                }else{
                    user.setActive(false);
                }

                user.setMobileNumber(Long.valueOf(updateUser.getMobileNumber()));
                user.setUserName(updateUser.getUsername());
                user.setFirstName(updateUser.getFirstName());
                user.setLastName(updateUser.getLastName());
                user.setStateId(Integer.parseInt(updateUser.getStateId()));
                user.setDistrictId(Integer.parseInt(updateUser.getDistrictId()));
                user.setBeatId(Integer.parseInt(updateUser.getBeatId()));
                user.setPassword(encoder.encode(updateUser.getPassword()));
                String roleIid = updateUser.getRoleId();

                user.setSosdpoId(Integer.parseInt(updateUser.getSosdpoId()));
                user.setPsId(Integer.parseInt(updateUser.getPsId()));
                user.setBeatId(Integer.parseInt(updateUser.getBeatId()));
                user.setRank(updateUser.getRank());

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());

                int saved = userRepository.updateUser(user.getFirstName(),
                        user.getLastName(),
                        user.getUserName(),
                        user.getPassword(),
                        BigInteger.valueOf(user.getMobileNumber()),
                        user.getStateId(),
                        user.getDistrictId(),
                        user.getBeatId(),
                        user.isActive(),
                        date,
                        Integer.parseInt(updateUser.getUserId()),
                        user.getSosdpoId(),
                        user.getPsId(),
                        user.getRank());

                if(saved == 1){
                    //Update Roles Mapping Table
                    int saveRole = userRepository.updateRole(Integer.parseInt(updateUser.getRoleId()),Integer.parseInt(updateUser.getUserId()),updateUser.getOldroleid());

                    request.getSession().setAttribute("successMessage", "Successfully Updated.");
                    updateUser.setMobileNumber("");
                    updateUser.setPasswordConfirm("");
                    updateUser.setPassword("");
                    updateUser.setUsername("");
                    updateUser.setRoleId("0");
                    return "redirect:/viewUsers";
                }else{
                    request.getSession().setAttribute("serverError", "Data Not Updated.");
                    updateUser.setMobileNumber("");
                    updateUser.setPasswordConfirm("");
                    updateUser.setPassword("");
                    updateUser.setUsername("");
                    updateUser.setRoleId("0");
                    return "redirect:/viewUsers";
                }






            } catch (Exception ex) {
                updateUser.setMobileNumber("");
                updateUser.setPasswordConfirm("");
                updateUser.setUsername("");
                updateUser.setPassword("");
                model.addAttribute("serverError", ex.toString());
                return "updateUser";
            }
        }

    }


    @RequestMapping(value = "/createRole", method = RequestMethod.GET)
    public String createRole(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{


                model.addAttribute("rolesForm", new RolesForm());
                return "createrole";
            }



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


    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model,HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("registerUser", new RegisterUser());
                return "createuser";
            }



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
                user.setSosdpoId(Integer.parseInt(registerUser.getSosdpoId()));
                user.setPsId(Integer.parseInt(registerUser.getPsId()));
                user.setBeatId(Integer.parseInt(registerUser.getBeatId()));
                user.setRank(registerUser.getRank());
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
}
