package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.*;
import com.hp.dit.police.inventory.form.itemcat.ItemCatForm;
import com.hp.dit.police.inventory.form.itemcat.UpdateItemCat;
import com.hp.dit.police.inventory.form.items.ItemForm;
import com.hp.dit.police.inventory.form.items.UpdateItem;
import com.hp.dit.police.inventory.form.policestation.PSForm;
import com.hp.dit.police.inventory.modals.ItemList;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.itemcategory.ItemCategoryRepository;
import com.hp.dit.police.inventory.repositories.items.ItemsRepository;
import com.hp.dit.police.inventory.services.FileStorageService;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryUpdateValidator;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryValidator;
import com.hp.dit.police.inventory.validators.itemValidator.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    ItemValidator itemValidator;

    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/createItem", method = RequestMethod.GET)
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

                model.addAttribute("itemForm", new ItemForm());
                return "createItem";
            }



        }
    }

    //saveItem
    @Transactional
    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("itemForm") ItemForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        itemValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            ItemsEntity entitySaved = null;
            if (bindingResult.hasErrors()) {
                return "createItem";
            }

            try {
                ItemsEntity ps = new ItemsEntity();
                ps.setItemsName(form.getItemsName().toString());
                ps.setItemsDesc(form.getItemsDesc().toString());
                ps.setItemsLetterno(form.getItemsLetterrefno());
                ps.setActive(true);
                ps.setDeleted(false);


                StoreEntity store = new StoreEntity();
                store.setStoreID(Integer.parseInt(form.getStoreId()));
                ps.setStore(store);

                CategoryItemsEntity categoryItemsEntity = new CategoryItemsEntity();
                categoryItemsEntity.setCategoryId(Integer.parseInt(form.getCategoryId()));
                ps.setCategoryItemsEntity(categoryItemsEntity);

                UnitsEntity units = new UnitsEntity();
                units.setUnitId(Integer.parseInt(form.getUnitId()));
                ps.setUnits(units);

                if (!form.getItemsLetterrefdoc().getOriginalFilename().isEmpty()) {
                    String fileName = StringUtils.cleanPath(form.getItemsLetterrefdoc().getOriginalFilename());
                    fileName = fileName.toLowerCase().replaceAll(" ", "_");
                    fileName = System.currentTimeMillis() + "__" + fileName;
                    form.setFileName(fileName);
                    fileStorageService.storeFile(form.getItemsLetterrefdoc(), fileName);
                    ps.setItemsLetterdoc(form.getFileName());
                } else {
                    form.setFileName("");
                }



                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                ps.setCreatedDate(date);

                entitySaved = itemsRepository.save(ps);
                form.setItemsName("");
                form.setItemsDesc("");
                form.setItemsLetterrefno("");
                request.getSession().setAttribute("successMessage", "Item Saved Successfully. Generated Item Id is:- " + entitySaved.getItemsId());

                return "createItem";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createItem";
            }
        }
    }

    //View Items
    @RequestMapping(value = "/viewItem", method = RequestMethod.GET)
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

                List<ItemList> categories = itemsRepository.getAllItemsSelectedColumns();
                System.out.println(categories.toString());
                model.addAttribute("item", categories);
                return "viewItem";
            }



        }
    }

    @RequestMapping(value = "/updateItem/{item_id}", method = RequestMethod.GET)
    public String updateItem(@PathVariable("item_id")Integer id, Model model,HttpServletRequest request) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if (user == null) {
                return "login";
            } else {


                ItemsEntity item = itemsRepository.getItemViaItemId(id);
                System.out.println(item.toString());
                model.addAttribute("item_to_update", item);
                model.addAttribute("updateItem", new UpdateItem());
                return "updateItem";
            }


        }
    }


    //saveItemEntity
    @RequestMapping(value = "/saveItemEntity", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String updateSubModuleEntity(@ModelAttribute("updateItem") UpdateItem form,
                                        BindingResult bindingResult, Model model, HttpServletRequest request,
                                        HttpSession session, RedirectAttributes redirectAttributes) {


        // moduleValidator.validate(submoduleForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateItem";
        }
        try {

            System.out.println(form.toString());

            ItemsEntity savedEntity = null;
            ItemsEntity itemToUpdate = new ItemsEntity();

            itemToUpdate = itemsRepository.getItemViaItemId(Integer.parseInt(form.getItemsId()));


            itemToUpdate.setItemsName(form.getItemsName().toString());
            itemToUpdate.setItemsDesc(form.getItemsDesc().toString());
            itemToUpdate.setItemsLetterno(form.getItemsLetterrefno());



            StoreEntity store = new StoreEntity();
            store.setStoreID(Integer.parseInt(form.getStoreId()));
            itemToUpdate.setStore(store);

            CategoryItemsEntity categoryItemsEntity = new CategoryItemsEntity();
            categoryItemsEntity.setCategoryId(Integer.parseInt(form.getCategoryId()));
            itemToUpdate.setCategoryItemsEntity(categoryItemsEntity);

            UnitsEntity units = new UnitsEntity();
            units.setUnitId(Integer.parseInt(form.getUnitId()));
            itemToUpdate.setUnits(units);


            if (form.getActive().equalsIgnoreCase("T")) {
                itemToUpdate.setActive(true);
            } else {
                itemToUpdate.setActive(false);
            }

            if (form.getDeleted().equalsIgnoreCase("T")) {
                itemToUpdate.setDeleted(true);
            } else {
                itemToUpdate.setDeleted(false);
            }

            if (!form.getItemsLetterrefdoc().getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(form.getItemsLetterrefdoc().getOriginalFilename());
                fileName = fileName.toLowerCase().replaceAll(" ", "_");
                fileName = System.currentTimeMillis() + "__" + fileName;
                itemToUpdate.setItemsLetterdoc(fileName);
                fileStorageService.storeFile(form.getItemsLetterrefdoc(), fileName);
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
            itemToUpdate.setLastmodifiedDate(date);

            savedEntity = itemsRepository.save(itemToUpdate);
            form.setItemsName("");
            form.setItemsDesc("");
            request.getSession().setAttribute("successMessage", "Item Updated Successfully. ");
            return "redirect:/viewItem";
        } catch (Exception ex) {
            request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
            return "updateItem";
        }

    }

}
