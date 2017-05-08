package com.liuyf.demos.spring.web.mvc.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.liuyf.demos.spring.web.mvc.model.User;

//@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("++++++++++++++============================");
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        System.out.println("00000000000000000000000000000000000000000");
        errors.rejectValue("noField", "NO_FIELD");
    }

}
