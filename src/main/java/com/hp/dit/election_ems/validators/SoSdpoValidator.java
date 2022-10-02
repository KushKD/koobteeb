package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.sosdpo.BankForm;
import com.hp.dit.election_ems.repositories.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SoSdpoValidator implements Validator {

    @Autowired
    BankRepository bankRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return BankForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BankForm user = (BankForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soSdpo", "NotEmpty");


        if (bankRepository.sdpoCount(user.getSoSdpo()) >0) {
            errors.rejectValue("soSdpo", "Duplicate.SoSdpoForm.soSdpo");
        }


    }
}
