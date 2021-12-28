package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.StoreEntity;
import com.hp.dit.police.inventory.form.store.StoreForm;
import com.hp.dit.police.inventory.form.store.UpdateStore;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.store.StoreRepository;
import com.hp.dit.police.inventory.validators.StoreValidator;
import com.hp.dit.police.inventory.validators.StoreValidatorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class StoreController {

    @Autowired
    StoreRepository categoryRepository;

    @Autowired
    StoreValidator storeValidator;

    @Autowired
    StoreValidatorUpdate storeValidatorUpdate;

    @RequestMapping(value = "/createCategory", method = RequestMethod.GET)
    public String createCategory(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if (user == null) {
                return "login";
            } else {
                model.addAttribute("categoryForm", new StoreForm());
                return "createCategory";
            }


        }
    }

    @Transactional
    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveState(@ModelAttribute("categoryForm") StoreForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        storeValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            StoreEntity savedCategory = null;
            if (bindingResult.hasErrors()) {
                return "createCategory";
            }

            try {
                StoreEntity category = new StoreEntity();
                category.setStoreName(form.getCategoryName().toString());
                category.setActive(true);
                category.setDeleted(false);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                category.setCreatedOn(date);
                savedCategory = categoryRepository.save(category);
                form.setCategoryName("");
                request.getSession().setAttribute("successMessage", "Category Saved Successfully. Generated State Id is:- " + savedCategory.getStoreID());

                return "createCategory";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createCategory";
            }
        }


    }

    @RequestMapping(value = "/viewCategory", method = RequestMethod.GET)
    public String viewStates(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if (user == null) {
                return "login";
            } else {

                List<StoreEntity> categories = categoryRepository.getAllCategories();
                model.addAttribute("categories", categories);
                return "viewCategory";
            }


        }
    }

    @RequestMapping(value = "/updateCategory/{category_id}", method = RequestMethod.GET)
    public String updateState(@PathVariable("category_id") Integer category_id, Model model, HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if (user == null) {
                return "login";
            } else {

                StoreEntity category = categoryRepository.getCategoryViaCategoryId(category_id);
                System.out.println(category.toString());
                model.addAttribute("category_to_update", category);
                model.addAttribute("updateStore", new UpdateStore());
                return "updateCategory";
            }


        }

    }

    @Transactional
    @RequestMapping(value = "/updateCategoryEntity", method = RequestMethod.POST)
    public String updateStateEntry(@ModelAttribute("updateStore") UpdateStore form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        storeValidatorUpdate.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            StoreEntity savedCategory = null;
            if (bindingResult.hasErrors()) {
                return "createState";
            }

            try {


                StoreEntity category = new StoreEntity();

                category = categoryRepository.getCategoryViaCategoryId(Integer.parseInt(form.getStoreId()));

                category.setStoreName(form.getStoreName().toString());
                category.setStoreID(Integer.parseInt(form.getStoreId()));

                if (form.getActive().equalsIgnoreCase("T")) {
                    category.setActive(true);
                } else {
                    category.setActive(false);
                }

                if (form.getDeleted().equalsIgnoreCase("T")) {
                    category.setDeleted(true);
                } else {
                    category.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                category.setCreatedOn(date);
                savedCategory = categoryRepository.save(category);
                form.setStoreName("");
                request.getSession().setAttribute("successMessage", "Category Updated.");

                return "redirect:/viewCategory";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createCategory";
            }

        }
    }
}