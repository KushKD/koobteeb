package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.sosdpo.SoSdpoForm;
import com.hp.dit.police.inventory.repositories.sosdpo.SoSdpoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SoSdpoValidator implements Validator {

    @Autowired
    SoSdpoRepository soSdpoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return SoSdpoForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SoSdpoForm user = (SoSdpoForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "soSdpo", "NotEmpty");


        if (soSdpoRepository.sdpoCount(user.getSoSdpo()) >0) {
            errors.rejectValue("soSdpo", "Duplicate.SoSdpoForm.soSdpo");
        }


    }
}
