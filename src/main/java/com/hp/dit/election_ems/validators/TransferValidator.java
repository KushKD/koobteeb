package com.hp.dit.election_ems.validators;

import com.hp.dit.election_ems.entities.TransferRequestEntities;
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


    @Override
    public boolean supports(Class<?> aClass) {
        return TransferForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TransferForm user = (TransferForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vehicleNo", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thruDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sourceAddress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destAddress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotEmpty");




    }
}
