package com.hp.dit.police.inventory.validators.itemValidator;

import com.hp.dit.police.inventory.form.itemcat.ItemCatForm;
import com.hp.dit.police.inventory.form.items.ItemForm;
import com.hp.dit.police.inventory.form.policestation.PSForm;
import com.hp.dit.police.inventory.repositories.itemcategory.ItemCategoryRepository;
import com.hp.dit.police.inventory.repositories.items.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Autowired
    ItemsRepository itemsRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PSForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemForm user = (ItemForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemsName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemsDesc", "NotEmpty");


        if (itemsRepository.itemCount(user.getItemsName()) >0) {
            errors.rejectValue("itemsName", "Duplicate.itemForm.itemsName");
        }


    }
}
