package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.store.UpdateStore;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StoreValidatorUpdate implements Validator {

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateStore user = (UpdateStore) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeName", "NotEmpty");


    }
}
