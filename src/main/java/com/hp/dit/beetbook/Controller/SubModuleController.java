package com.hp.dit.beetbook.Controller;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import com.hp.dit.beetbook.form.module.ModuleForm;
import com.hp.dit.beetbook.form.submodule.SubModuleForm;
import com.hp.dit.beetbook.repositories.modules.ModuleRepository;
import com.hp.dit.beetbook.repositories.submodules.modules.SubModuleRepository;
import com.hp.dit.beetbook.services.FileStorageService;
import com.hp.dit.beetbook.validators.ModuleValidator;
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
import java.util.List;

@Controller
public class SubModuleController {

    @Autowired
    SubModuleRepository subModuleRepository;

    @Autowired
    ModuleValidator moduleValidator;

    @Autowired
    private FileStorageService fileStorageService;


    @RequestMapping(value = "/createsubmodule", method = RequestMethod.GET)
    public String createDistrict(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            model.addAttribute("subModuleForm", new SubModuleForm());
            return "createsubmodule";
        }
    }

    @RequestMapping(value = "/savesubModule", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("subModuleForm") SubModuleForm submoduleForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


      //  moduleValidator.validate(moduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createsubmodule";
        }
        try {
            SubModuleMaster submodule = new SubModuleMaster();
            SubModuleMaster savedModule = null;
            submodule.setSubmoduleName(submoduleForm.getSubmoduleName().toString());
            submodule.setModuleId(Integer.parseInt(submoduleForm.getModuleId()));

            if (submoduleForm.getIsActive().equalsIgnoreCase("T")) {
                submodule.setActive(true);
            } else {
                submodule.setActive(false);
            }

            if (submoduleForm.getIsDeleted().equalsIgnoreCase("T")) {
                submodule.setDeleted(true);
            } else {
                submodule.setDeleted(false);
            }

            if (!submoduleForm.getSubmoduleIcon().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(submoduleForm.getSubmoduleIcon().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                submodule.setSubiconName(fileName);
                fileStorageService.storeFile(submoduleForm.getSubmoduleIcon(), fileName);
            } else {
                submodule.setSubiconName("");
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            submodule.setCreatedDate(date);

            savedModule = subModuleRepository.save(submodule);
            submoduleForm.setSubmoduleName("");
            request.getSession().setAttribute("successMessage", "Sub Module Saved Successfully. Generated Sub Module Id is:- " + savedModule.getSubmoduleId());
            return "createsubmodule";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "createsubmodule";
        }

    }

    @RequestMapping(value = "/viewsubmodule", method = RequestMethod.GET)
    public String viewModule(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            List<SubModuleMaster> modules = subModuleRepository.getAllSubModules();
            model.addAttribute("submodules", modules);
            return "viewsubmodule";
        }
    }

    //updateModule
    @RequestMapping(value = "/updatesubModule/{district_id}", method = RequestMethod.GET)
    public String updateDistrict(@PathVariable("district_id")Integer submodule_id, Model model) throws Exception {

        System.out.println(submodule_id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            SubModuleMaster submodule = subModuleRepository.getSubModuleViaId(submodule_id);
            System.out.println(submodule.toString());
            model.addAttribute("submodule_to_update", submodule);
            model.addAttribute("subModuleForm", new SubModuleForm());
            return "updatesubmodule";

        }
    }


    @RequestMapping(value = "/updateSubModuleEntity", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String updateSubModuleEntity(@ModelAttribute("subModuleForm") SubModuleForm submoduleForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


       // moduleValidator.validate(submoduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updatesubmodule";
        }
        try {

            SubModuleMaster savedModule = null;
            SubModuleMaster moduleToUpdate = new SubModuleMaster();

            moduleToUpdate = subModuleRepository.getSubModuleViaId(Integer.parseInt(submoduleForm.getSubmoduleId()));

            moduleToUpdate.setSubmoduleName(submoduleForm.getSubmoduleName().toString());
            moduleToUpdate.setModuleId(Integer.parseInt(submoduleForm.getModuleId()));

            if (submoduleForm.getIsActive().equalsIgnoreCase("T")) {
                moduleToUpdate.setActive(true);
            } else {
                moduleToUpdate.setActive(false);
            }

            if (submoduleForm.getIsDeleted().equalsIgnoreCase("T")) {
                moduleToUpdate.setDeleted(true);
            } else {
                moduleToUpdate.setDeleted(false);
            }

            if (!submoduleForm.getSubmoduleIcon().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(submoduleForm.getSubmoduleIcon().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                moduleToUpdate.setSubiconName(fileName);
                fileStorageService.storeFile(submoduleForm.getSubmoduleIcon(), fileName);
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            moduleToUpdate.setCreatedDate(date);

            savedModule = subModuleRepository.save(moduleToUpdate);
            submoduleForm.setSubmoduleName("");
            request.getSession().setAttribute("successMessage", "Sub Module Updated Successfully. ");
            return "redirect:/viewsubmodule";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "updatesubmodule";
        }

    }

}
