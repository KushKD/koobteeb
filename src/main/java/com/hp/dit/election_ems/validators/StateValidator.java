package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.user.RegisterUser;
import com.hp.dit.election_ems.form.state.StateForm;
import com.hp.dit.election_ems.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StateValidator implements Validator {

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StateForm user = (StateForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateName", "NotEmpty");


        if (stateRepository.stateCount(user.getStateName()) >0) {
            errors.rejectValue("stateName", "Duplicate.StateForm.stateName");
        }


    }
}
