package com.hp.dit.election_ems.Controller.roles;

import com.hp.dit.election_ems.entities.RolesEntity;
import com.hp.dit.election_ems.form.RolesForm;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.roles.RolesRepositoryDatatables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class RolesController {

    @Autowired
    RolesRepositoryDatatables rolesRepository;



    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    public String viewBarrier(Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "getRoles";
            }
        }
    }



    //updateModule
    @RequestMapping(value = "/updateRole/{district_id}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("district_id")Integer role_id, Model model, HttpServletRequest request) throws Exception {

        System.out.println(role_id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                RolesEntity role =rolesRepository.getRoleViaId(role_id);
                System.out.println(role.toString());
                model.addAttribute("role_to_update", role);
                model.addAttribute("rolesForm", new RolesForm());
                return "updatrole";
            }
        }
    }

    @RequestMapping(value = "/updateRoleEntity", method = RequestMethod.POST)
    @Transactional
    public String updateModuleEntity(@ModelAttribute("rolesForm") RolesForm rolesForm,
                                     BindingResult bindingResult, Model model, HttpServletRequest request,
                                     HttpSession session, RedirectAttributes redirectAttributes) {


       // moduleValidatorUpdate.validate(moduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "rolesForm";
        }
        try {

            RolesEntity savedModule = null;
            RolesEntity roleToUpdate = new RolesEntity();

            roleToUpdate = rolesRepository.getRoleViaId(Integer.parseInt(rolesForm.getRoleId()));

            roleToUpdate.setRoleName(rolesForm.getRoleName().toString());
            roleToUpdate.setRoleDescription(rolesForm.getRoleDescription());

            if (rolesForm.getIsActive().equalsIgnoreCase("T")) {
                roleToUpdate.setActive(true);
            } else {
                roleToUpdate.setActive(false);
            }


            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            roleToUpdate.setUpdatedOn(date);

            savedModule = rolesRepository.save(roleToUpdate);
            rolesForm.setRoleName("");
            rolesForm.setRoleDescription("");
            request.getSession().setAttribute("successMessage", "Role Updated Successfully. ");
            return "redirect:/getRoles";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "updatemodule";
        }

    }



}
