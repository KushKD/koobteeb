package com.hp.dit.police.inventory.validators.unitValidator;

import com.hp.dit.police.inventory.form.store.UpdateStore;
import com.hp.dit.police.inventory.form.units.UpdateUnit;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.units.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UnitValidatorUpdate implements Validator {

    @Autowired
    UnitsRepository unitsRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateUnit user = (UpdateUnit) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitDescription", "NotEmpty");


    }
}
