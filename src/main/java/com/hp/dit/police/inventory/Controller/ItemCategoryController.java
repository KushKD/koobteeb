package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.PoliceStationMaster;
import com.hp.dit.police.inventory.form.itemcat.ItemCatForm;
import com.hp.dit.police.inventory.form.itemcat.UpdateItemCat;
import com.hp.dit.police.inventory.form.policestation.PSForm;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.itemcategory.ItemCategoryRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import com.hp.dit.police.inventory.validators.PoliceStationUpdateValidator;
import com.hp.dit.police.inventory.validators.PoliceStationValidator;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryUpdateValidator;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryValidator;
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
public class ItemCategoryController {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    CategoryValidator categoryValidator;

    @Autowired
    CategoryUpdateValidator categoryUpdateValidator;

    //ItemCatForm

    @RequestMapping(value = "/createItemCategory", method = RequestMethod.GET)
    public String createPS(Model model,HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                model.addAttribute("itemCatForm", new ItemCatForm());
                return "createItemCategory";
            }



        }
    }

    //savePs
    @Transactional
    @RequestMapping(value = "/saveItemCategory", method = RequestMethod.POST)
    public String saveState(@ModelAttribute("itemCatForm") ItemCatForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        categoryValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            CategoryItemsEntity catSaved = null;
            if (bindingResult.hasErrors()) {
                return "createItemCategory";
            }

            try {
                CategoryItemsEntity ps = new CategoryItemsEntity();
                ps.setCategoryName(form.getCategoryName().toString());
                ps.setCategoryDescription(form.getCategoryDesc().toString());
                ps.setActive(true);
                ps.setDeleted(false);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                ps.setCreatedDate(date);
                catSaved = itemCategoryRepository.save(ps);
                form.setCategoryDesc("");
                form.setCategoryDesc("");
                request.getSession().setAttribute("successMessage", "Category Saved Successfully. Generated Category Id is:- " + catSaved.getCategoryId());

                return "createItemCategory";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createItemCategory";
            }
        }
    }



    @RequestMapping(value = "/viewItemCategory", method = RequestMethod.GET)
    public String viewStates(Model model,HttpServletRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{

                List<CategoryItemsEntity> categories = itemCategoryRepository.getAllCategories();
                model.addAttribute("item_category", categories);
                return "viewItemCategory";
            }



        }
    }

    @RequestMapping(value = "/updateItemCategory/{item_cat_id}", method = RequestMethod.GET)
    public String updateState(@PathVariable("item_cat_id")Integer id, Model model,HttpServletRequest request) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{


                CategoryItemsEntity state = itemCategoryRepository.getCategoryViaCategoryId(id);
                System.out.println(state.toString());
                model.addAttribute("category_to_update", state);
                model.addAttribute("updateItemCat", new UpdateItemCat());
                return "updateItemCategory";
            }




        }

    }

    //updatePs
    @Transactional
    @RequestMapping(value = "/saveItemCategoryEntity", method = RequestMethod.POST)
    public String updatePs(@ModelAttribute("updateItemCat") UpdateItemCat form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        categoryUpdateValidator.validate(form, bindingResult);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            CategoryItemsEntity savedEntity = null;
            if (bindingResult.hasErrors()) {
                return "updatePs";
            }

            try {

                //Get State Data via ID

                CategoryItemsEntity ps = new CategoryItemsEntity();

                ps = itemCategoryRepository.getCategoryViaCategoryId(Integer.parseInt(form.getCategoryId()));

                ps.setCategoryName(form.getCategoryName().toString());
                ps.setCategoryDescription(form.getCategoryDesc().toString());



                if (form.getCategoryIsActive().equalsIgnoreCase("T")) {
                    ps.setActive(true);
                } else {
                    ps.setActive(false);
                }

                if (form.getCategoryIsDeleted().equalsIgnoreCase("T")) {
                    ps.setDeleted(true);
                } else {
                    ps.setDeleted(false);
                }
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                ps.setCreatedDate(date);
                savedEntity = itemCategoryRepository.save(ps);
                form.setCategoryName("");
                form.setCategoryDesc("");
                request.getSession().setAttribute("successMessage", "Category Updated.");

                return "redirect:/viewItemCategory";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "updateItemCategory";
            }

        }
    }
}
