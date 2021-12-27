package com.hp.dit.police.inventory.validators;

import com.hp.dit.police.inventory.form.category.CategoryForm;
import com.hp.dit.police.inventory.form.state.StateForm;
import com.hp.dit.police.inventory.form.user.RegisterUser;
import com.hp.dit.police.inventory.repositories.category.CategoryRepository;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryForm user = (CategoryForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty");


        if (categoryRepository.categoryCount(user.getCategoryName()) >0) {
            errors.rejectValue("categoryName", "Duplicate.CategoryForm.categoryName");
        }


    }
}
