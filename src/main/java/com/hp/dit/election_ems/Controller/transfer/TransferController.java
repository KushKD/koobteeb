package com.hp.dit.election_ems.Controller.transfer;

import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.form.module.ModuleForm;
import com.hp.dit.election_ems.form.transfer.TransferForm;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.validators.ModuleValidator;
import com.hp.dit.election_ems.validators.TransferValidator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class TransferController {

    @Autowired
    TransferValidator transferValidator;

    @RequestMapping(value = "/createtransfer", method = RequestMethod.GET)
    public String createTransfer(Model model, HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("transferForm", new TransferForm());
                return "createTransfer";
            }

        }
    }


    @RequestMapping(value = "/saveTransfer", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String saveDetails(@ModelAttribute("transferForm") TransferForm transferForm,
                              BindingResult bindingResult, Model model, HttpServletRequest request,
                              HttpSession session, RedirectAttributes redirectAttributes) {


        transferValidator.validate(transferForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createTransfer";
        }
        try {
//            ModuleMaster module = new ModuleMaster();
//            ModuleMaster savedModule = null;
//            module.setModuleName(moduleForm.getModuleName().toString());
//
//            if (moduleForm.getIsActive().equalsIgnoreCase("T")) {
//                module.setActive(true);
//            } else {
//                module.setActive(false);
//            }
//
//            if (moduleForm.getIsDeleted().equalsIgnoreCase("T")) {
//                module.setDeleted(true);
//            } else {
//                module.setDeleted(false);
//            }
//
//            if (!moduleForm.getModuleIcon().getOriginalFilename().isEmpty()) {
//                String fileName = StringUtils.cleanPath(moduleForm.getModuleIcon().getOriginalFilename());
//                fileName = fileName.toLowerCase().replaceAll(" ", "_");
//                fileName = System.currentTimeMillis() + "__" + fileName;
//                module.setIconName(fileName);
//                fileStorageService.storeFile(moduleForm.getModuleIcon(), fileName);
//            } else {
//                module.setIconName("");
//            }
//
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            Date date = new Date(timestamp.getTime());
//            module.setCreatedDate(date);
//
//            savedModule = moduleRepository.save(module);
//            moduleForm.setModuleName("");
//            request.getSession().setAttribute("successMessage", "Module Saved Successfully. Generated Module Id is:- " + savedModule.getModuleId());
            return "createTransfer";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "createTransfer";
        }

    }

}
