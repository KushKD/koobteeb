package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.*;
import com.hp.dit.police.inventory.form.itemcat.ItemCatForm;
import com.hp.dit.police.inventory.form.itemcat.UpdateItemCat;
import com.hp.dit.police.inventory.form.items.ItemForm;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.itemcategory.ItemCategoryRepository;
import com.hp.dit.police.inventory.repositories.items.ItemsRepository;
import com.hp.dit.police.inventory.services.FileStorageService;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryUpdateValidator;
import com.hp.dit.police.inventory.validators.categoryValidator.CategoryValidator;
import com.hp.dit.police.inventory.validators.itemValidator.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
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
                ps.setQuantity(Integer.parseInt(form.getQuantity()));
                ps.setItemsLetterno(form.getItemsLetterrefno());
                ps.setActive(true);
                ps.setDeleted(false);

                PoliceLines policeLines =new PoliceLines();
                policeLines.setPolicelineId(Integer.parseInt(form.getPolicelineId()));
                ps.setPoliceLines(policeLines);

                PoliceStationMaster policeStationMaster = new PoliceStationMaster();
                policeStationMaster.setPsId(Integer.parseInt(form.getPsId()));
                ps.setPoliceStationMaster(policeStationMaster);

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
                form.setQuantity("");
                form.setItemsLetterrefno("");
                request.getSession().setAttribute("successMessage", "Item Saved Successfully. Generated Item Id is:- " + entitySaved.getItemsId());

                return "createItem";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "createItem";
            }
        }
    }


}
