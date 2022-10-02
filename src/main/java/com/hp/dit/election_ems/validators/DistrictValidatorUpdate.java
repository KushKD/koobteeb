package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.user.RegisterUser;
import com.hp.dit.election_ems.form.district.DistrictForm;
import com.hp.dit.election_ems.repositories.districtRepository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DistrictValidatorUpdate implements Validator {

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DistrictForm district = (DistrictForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "districtName", "NotEmpty");


    }
}
