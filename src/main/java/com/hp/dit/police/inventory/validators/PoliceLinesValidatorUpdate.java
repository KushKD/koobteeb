package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.sosdpo.SoSdpoUpdate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PoliceLinesValidatorUpdate implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return SoSdpoUpdate.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SoSdpoUpdate user = (SoSdpoUpdate) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "policelineName", "NotEmpty");


    }
}