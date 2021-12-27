package com.hp.dit.police.inventory.validators.unitValidator;

import com.hp.dit.police.inventory.form.store.StoreForm;
import com.hp.dit.police.inventory.form.units.UnitForm;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.store.StoreRepository;
import com.hp.dit.police.inventory.repositories.units.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UnitValidator implements Validator {

    @Autowired
    UnitsRepository unitsRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UnitForm user = (UnitForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitName", "NotEmpty");


        if (unitsRepository.unitCount(user.getUnitName()) >0) {
            errors.rejectValue("unitName", "Duplicate.UnitForm.unitName");
        }


    }
}
