package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.sosdpo.BankUpdate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SoSdpoValidatorUpdate implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return BankUpdate.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BankUpdate user = (BankUpdate) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soSdpoName", "NotEmpty");


    }
}
