package com.hp.dit.beetbook.validators;

import com.hp.dit.beetbook.form.policestation.PSForm;
import com.hp.dit.beetbook.repositories.policestationRepository.PSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PoliceStationUpdateValidator implements Validator {

    @Autowired
    PSRepository psRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PSForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PSForm user = (PSForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "psName", "NotEmpty");




    }
}
