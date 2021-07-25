package com.hp.dit.beetbook.validators;

import com.hp.dit.beetbook.form.RegisterUser;
import com.hp.dit.beetbook.form.sosdpo.SoSdpoForm;
import com.hp.dit.beetbook.form.sosdpo.SoSdpoUpdate;
import com.hp.dit.beetbook.form.state.UpdateState;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SoSdpoValidatorUpdate implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return SoSdpoUpdate.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SoSdpoUpdate user = (SoSdpoUpdate) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soSdpoName", "NotEmpty");


    }
}
