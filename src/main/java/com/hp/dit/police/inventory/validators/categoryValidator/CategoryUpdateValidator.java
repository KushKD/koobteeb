package com.hp.dit.police.inventory.validators.categoryValidator;

import com.hp.dit.police.inventory.form.itemcat.UpdateItemCat;
import com.hp.dit.police.inventory.form.policestation.PSForm;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryUpdateValidator implements Validator {

    @Autowired
    PSRepository psRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PSForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateItemCat user = (UpdateItemCat) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty");




    }
}
