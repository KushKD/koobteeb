package com.hp.dit.beetbook.validators;

import com.hp.dit.beetbook.CustomLogin.CustomUserService;
import com.hp.dit.beetbook.form.SaarthiForm;
import com.hp.dit.beetbook.form.VahanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SaarthiServiceValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SaarthiForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SaarthiForm user = (SaarthiForm) o;



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dlNumber", "NotEmpty");
        if(user.getDlNumber().equalsIgnoreCase("")){
            errors.rejectValue("dlNumber", "Select.sarthi.dlNumber");
        }


    }
}
