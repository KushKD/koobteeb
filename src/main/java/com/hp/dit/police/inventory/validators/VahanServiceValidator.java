package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.CustomLogin.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VahanServiceValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return VahanService.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VahanService user = (VahanService) o;



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameter", "NotEmpty");



        if(user.getServiceType().equalsIgnoreCase("")){
            errors.rejectValue("serviceType", "Select.vehicleService.serviceType");
        }


    }
}
