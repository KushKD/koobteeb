package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.form.state.UpdateState;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StateValidatorUpdate implements Validator {

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdateState user = (UpdateState) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateName", "NotEmpty");


//        if (stateRepository.stateCount(user.getStateName()) >0) {
//            errors.rejectValue("stateName", "Duplicate.UpdateState.stateName");
//        }


    }
}
