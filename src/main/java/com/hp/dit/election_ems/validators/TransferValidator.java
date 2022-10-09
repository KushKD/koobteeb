package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.form.module.ModuleForm;
import com.hp.dit.election_ems.form.transfer.TransferForm;
import com.hp.dit.election_ems.repositories.modules.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TransferValidator implements Validator {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ModuleForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TransferForm user = (TransferForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "moduleName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "moduleIcon", "NotEmpty");


//        if (moduleRepository.moduleCount(user.getVehicleNo()) >0) {
//            errors.rejectValue("moduleName", "Duplicate.module.moduleName");
//        }


    }
}
