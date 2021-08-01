package com.hp.dit.beetbook.validators;

import com.hp.dit.beetbook.form.modulerole.ModuleRoleForm;
import com.hp.dit.beetbook.repositories.rolemodule.RoleModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ModuleRoleValidatorUpdate implements Validator {

    @Autowired
    RoleModuleRepository roleModuleRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ModuleRoleForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ModuleRoleForm user = (ModuleRoleForm) o;






    }
}
