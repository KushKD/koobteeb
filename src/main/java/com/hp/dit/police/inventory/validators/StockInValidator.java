package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.state.StateForm;
import com.hp.dit.police.inventory.form.stockregister.StockinForm;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StockInValidator implements Validator {

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StockinForm user = (StockinForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty");

        if(user.getItemId().equalsIgnoreCase("0")){
            errors.rejectValue("itemId", "Select.stockinForm.itemId");
        }



    }
}
