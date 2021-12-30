package com.hp.dit.police.inventory.Controller;

import com.hp.dit.police.inventory.entities.*;
import com.hp.dit.police.inventory.form.stockregister.StockinForm;
import com.hp.dit.police.inventory.form.store.StoreForm;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.repositories.stockin.StockInRepository;
import com.hp.dit.police.inventory.validators.StockInValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class StockInController {

    @Autowired
    StockInValidator stockInValidator;

    @Autowired
    StockInRepository stockInRepository;


    @RequestMapping(value = "/stockIn", method = RequestMethod.GET)
    public String createStock(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {

            LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
            System.out.println(user);

            if(user==null){
                return "login";
            }else{
                model.addAttribute("stockinForm", new StockinForm());
                return "stockIn";
            }



        }
    }

    @Transactional
    @RequestMapping(value = "/saveStockinItem", method = RequestMethod.POST)
    public String saveState(@ModelAttribute("stockinForm") StockinForm form, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        stockInValidator.validate(form, bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        } else {
            StockInEntity entitySaved = null;
            if (bindingResult.hasErrors()) {
                return "stockIn";
            }

            try {
                StockInEntity stockin = new StockInEntity();
                stockin.setQuantity(Integer.parseInt(form.getQuantity().toString()));
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                stockin.setCreatedDate(date);

                PoliceLines policeLines = new PoliceLines();
                policeLines.setPolicelineId(Integer.parseInt(form.getPolicelineId()));
                stockin.setPoliceLines(policeLines);

                ItemsEntity item = new ItemsEntity();
                item.setItemsId(Integer.parseInt(form.getItemId()));
                stockin.setItems(item);

                LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
                System.out.println(user);

                UserEntity userEntity =  new UserEntity();
                userEntity.setUserId(user.getUserId());
                stockin.setUser(userEntity);

                entitySaved = stockInRepository.save(stockin);
                form.setQuantity("");
                request.getSession().setAttribute("successMessage", "Stock added to Police Line. Generated  Id is:- " + entitySaved.getStockId());

                return "stockIn";
            } catch (Exception ex) {
                request.getSession().setAttribute("successMessage", ex.getLocalizedMessage());
                return "stockIn";
            }
        }


    }
}
