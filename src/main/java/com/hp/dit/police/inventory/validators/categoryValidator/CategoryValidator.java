package com.hp.dit.police.inventory.validators.categoryValidator;

import com.hp.dit.police.inventory.form.itemcat.ItemCatForm;
import com.hp.dit.police.inventory.form.policestation.PSForm;
import com.hp.dit.police.inventory.repositories.itemcategory.ItemCategoryRepository;
import com.hp.dit.police.inventory.repositories.policestationRepository.PSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PSForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemCatForm user = (ItemCatForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryDesc", "NotEmpty");


        if (itemCategoryRepository.categoryCount(user.getCategoryName()) >0) {
            errors.rejectValue("categoryName", "Duplicate.itemCatForm.categoryName");
        }


    }
}
