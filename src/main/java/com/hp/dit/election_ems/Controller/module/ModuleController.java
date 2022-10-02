package com.hp.dit.election_ems.Controller.module;

import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.form.module.ModuleForm;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.modules.ModuleRepository;
import com.hp.dit.election_ems.services.FileStorageService;
import com.hp.dit.election_ems.validators.ModuleValidator;
import com.hp.dit.election_ems.validators.ModuleValidatorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ModuleValidator moduleValidator;

    @Autowired
    ModuleValidatorUpdate moduleValidatorUpdate;

    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/createmodule", method = RequestMethod.GET)
    public String createDistrict(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                model.addAttribute("moduleForm", new ModuleForm());
                return "createmodule";
            }


        }
    }

    @RequestMapping(value = "/saveModule", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("moduleForm") ModuleForm moduleForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


        moduleValidator.validate(moduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createmodule";
        }
        try {
            ModuleMaster module = new ModuleMaster();
            ModuleMaster savedModule = null;
            module.setModuleName(moduleForm.getModuleName().toString());

            if (moduleForm.getIsActive().equalsIgnoreCase("T")) {
                module.setActive(true);
            } else {
                module.setActive(false);
            }

            if (moduleForm.getIsDeleted().equalsIgnoreCase("T")) {
                module.setDeleted(true);
            } else {
                module.setDeleted(false);
            }

            if (!moduleForm.getModuleIcon().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(moduleForm.getModuleIcon().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                module.setIconName(fileName);
                fileStorageService.storeFile(moduleForm.getModuleIcon(), fileName);
            } else {
                module.setIconName("");
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            module.setCreatedDate(date);

            savedModule = moduleRepository.save(module);
            moduleForm.setModuleName("");
            request.getSession().setAttribute("successMessage", "Module Saved Successfully. Generated Module Id is:- " + savedModule.getModuleId());
            return "createmodule";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "createmodule";
        }

    }

    @RequestMapping(value = "/viewmodule", method = RequestMethod.GET)
    public String viewModule(Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                return "viewmodule";
            }



        }
    }

    //updateModule
    @RequestMapping(value = "/updateModule/{district_id}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("district_id")Integer module_id, Model model, HttpServletRequest request) throws Exception {

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

                ModuleMaster module =moduleRepository.getModuleViaId(module_id);
                System.out.println(module.toString());
                model.addAttribute("module_to_update", module);
                model.addAttribute("moduleForm", new ModuleForm());
                return "updatemodule";
            }





        }
    }

    // updateModuleEntity
    @RequestMapping(value = "/updateModuleEntity", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String updateModuleEntity(@ModelAttribute("moduleForm") ModuleForm moduleForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


        moduleValidatorUpdate.validate(moduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updatemodule";
        }
        try {

            ModuleMaster savedModule = null;
            ModuleMaster moduleToUpdate = new ModuleMaster();

            moduleToUpdate = moduleRepository.getModuleViaId(Integer.parseInt(moduleForm.getModuleId()));

            moduleToUpdate.setModuleName(moduleForm.getModuleName().toString());

            if (moduleForm.getIsActive().equalsIgnoreCase("T")) {
                moduleToUpdate.setActive(true);
            } else {
                moduleToUpdate.setActive(false);
            }

            if (moduleForm.getIsDeleted().equalsIgnoreCase("T")) {
                moduleToUpdate.setDeleted(true);
            } else {
                moduleToUpdate.setDeleted(false);
            }

            if (!moduleForm.getModuleIcon().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(moduleForm.getModuleIcon().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                moduleToUpdate.setIconName(fileName);
                fileStorageService.storeFile(moduleForm.getModuleIcon(), fileName);
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            moduleToUpdate.setCreatedDate(date);

            savedModule = moduleRepository.save(moduleToUpdate);
            moduleForm.setModuleName("");
            request.getSession().setAttribute("successMessage", "Module Updated Successfully. ");
            return "redirect:/viewmodule";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "updatemodule";
        }

    }

}
