package com.hp.dit.beetbook.validators;

import com.hp.dit.beetbook.form.module.ModuleForm;
import com.hp.dit.beetbook.form.state.StateForm;
import com.hp.dit.beetbook.form.user.RegisterUser;
import com.hp.dit.beetbook.repositories.modules.ModuleRepository;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ModuleValidator implements Validator {

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


        if (moduleRepository.moduleCount(user.getModuleName()) >0) {
            errors.rejectValue("moduleName", "Duplicate.module.moduleName");
        }


    }
}
