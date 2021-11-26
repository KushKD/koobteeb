package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.ModuleMaster;
import com.hp.dit.police.inventory.entities.ModuleRoleMappingMaster;
import com.hp.dit.police.inventory.entities.RolesEntity;
import com.hp.dit.police.inventory.form.modulerole.ModuleRoleForm;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.modals.moduleRole.ModuleRoleList;
import com.hp.dit.police.inventory.repositories.rolemodule.RoleModuleRepository;
import com.hp.dit.police.inventory.validators.ModuleRoleValidator;
import com.hp.dit.police.inventory.validators.ModuleRoleValidatorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import java.util.List;

@Controller
public class ModuleRoleMapping {


    @Autowired
    RoleModuleRepository roleModuleRepository;

    @Autowired
    ModuleRoleValidator moduleRoleValidator;

    @Autowired
    ModuleRoleValidatorUpdate moduleRoleValidatorUpdate;

    @RequestMapping(value = "/createmodulerolemapping", method = RequestMethod.GET)
    public String createDistrict(Model model ,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("moduleRoleForm", new ModuleRoleForm());
                return "createmodulerolemapping";
            }



        }
    }

    //saveModuleRole
    @RequestMapping(value = "/saveModuleRole", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveModuleRole(@ModelAttribute("moduleRoleForm") ModuleRoleForm moduleRoleForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


        moduleRoleValidator.validate(moduleRoleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createmodulerolemapping";
        }
        try {
            ModuleRoleMappingMaster moduleRole = new ModuleRoleMappingMaster();
            ModuleRoleMappingMaster savedModuleRole = null;

            ModuleMaster module = new ModuleMaster();
            module.setModuleId(Integer.parseInt(moduleRoleForm.getModuleId()));
            moduleRole.setModuleId(module);

            RolesEntity role = new RolesEntity();
            role.setRoleId(Integer.parseInt(moduleRoleForm.getRoleId()));
            moduleRole.setRoleId(role);

            if (moduleRoleForm.getIsActive().equalsIgnoreCase("T")) {
                moduleRole.setActive(true);
            } else {
                moduleRole.setActive(false);
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            moduleRole.setCreatedDate(date);

            savedModuleRole = roleModuleRepository.save(moduleRole);
            request.getSession().setAttribute("successMessage", "Module Saved Successfully with Role. Generated  Id is:- " + savedModuleRole.getId());
            return "createmodulerolemapping";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "createmodulerolemapping";
        }

    }

    @RequestMapping(value = "/viewmodulerolemapping", method = RequestMethod.GET)
    public String viewModule(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                List<ModuleRoleList> modulesroles = roleModuleRepository.getAllActiveModulesViaRoles();
                System.out.println(modulesroles.toString());
                model.addAttribute("modulesroles", modulesroles);
                return "viewmodulerolemapping";
            }


        }
    }

    //updateModuleRoleEntity
    @RequestMapping(value = "/updatemodulerolemapping/{district_id}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("district_id")Integer module_id, Model model,HttpServletRequest request) throws Exception {

        System.out.println(module_id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                ModuleRoleList module =roleModuleRepository.getModuleRoleViaId(module_id);
                System.out.println(module.toString());
                model.addAttribute("module_to_update", module);
                model.addAttribute("moduleRoleForm", new ModuleRoleForm());
                return "updatemodulerolemapping";
            }



        }
    }

    //updateModuleRoleEntity
    // updateModuleEntity
    @RequestMapping(value = "/updateModuleRoleEntity", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String updateModuleRoleEntity(@ModelAttribute("moduleRoleForm") ModuleRoleForm moduleRoleForm,
                                     BindingResult bindingResult, Model model, HttpServletRequest request,
                                     HttpSession session, RedirectAttributes redirectAttributes) {


        moduleRoleValidatorUpdate.validate(moduleRoleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updatemodulerolemapping";
        }
        try {
            System.out.println(moduleRoleForm.toString());
            ModuleRoleMappingMaster moduleRoleMappingMaster = null;
            ModuleRoleMappingMaster moduleRoleToUpdate = new ModuleRoleMappingMaster();

            moduleRoleToUpdate = roleModuleRepository.getModuleRoleViaId_(Integer.parseInt(moduleRoleForm.getId()));

            ModuleMaster module = new ModuleMaster();
            module.setModuleId(Integer.parseInt(moduleRoleForm.getModuleId()));
            moduleRoleToUpdate.setModuleId(module);

            RolesEntity role = new RolesEntity();
            role.setRoleId(Integer.parseInt(moduleRoleForm.getRoleId()));
            moduleRoleToUpdate.setRoleId(role);

            if (moduleRoleForm.getIsActive().equalsIgnoreCase("T")) {
                moduleRoleToUpdate.setActive(true);
            } else {
                moduleRoleToUpdate.setActive(false);
            }



            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            moduleRoleToUpdate.setUpdatedOn(date);

            moduleRoleMappingMaster = roleModuleRepository.save(moduleRoleToUpdate);
            request.getSession().setAttribute("successMessage", "Module Updated Successfully. ");
            return "redirect:/viewmodulerolemapping";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "updatemodulerolemapping";
        }

    }

}
