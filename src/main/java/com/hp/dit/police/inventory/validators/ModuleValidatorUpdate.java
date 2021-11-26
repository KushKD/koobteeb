package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.module.ModuleForm;
import com.hp.dit.police.inventory.repositories.modules.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ModuleValidatorUpdate implements Validator {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ModuleForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ModuleForm user = (ModuleForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "moduleName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "moduleIcon", "NotEmpty");




    }
}
