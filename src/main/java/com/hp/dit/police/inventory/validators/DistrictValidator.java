package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.form.district.DistrictForm;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DistrictValidator implements Validator {

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


        if (districtRepository.districtCount(district.getDistrictName()) >0) {
            errors.rejectValue("districtName", "Duplicate.districtName.districtName");
        }


    }
}