package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.store.StoreForm;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreValidator implements Validator {

    @Autowired
    StoreRepository categoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StoreForm user = (StoreForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty");


        if (categoryRepository.categoryCount(user.getCategoryName()) >0) {
            errors.rejectValue("categoryName", "Duplicate.StoreForm.categoryName");
        }


    }
}