package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.modulerole.ModuleRoleForm;
import com.hp.dit.election_ems.repositories.rolemodule.RoleModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ModuleRoleValidator implements Validator {

    @Autowired
    RoleModuleRepository roleModuleRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ModuleRoleForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ModuleRoleForm user = (ModuleRoleForm) o;



        if (roleModuleRepository.moduleRoleCount(Integer.parseInt(user.getModuleId()),Integer.parseInt(user.getRoleId()) ) >0) {
            errors.rejectValue("moduleId", "Duplicate.module.moduleName");
        }


    }
}
