package com.liuyf.demos.spring.web.mvc.controllers.advices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class DataBinderAdvice {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        List<Validator> vas = new ArrayList<>();
        for(Validator validator : applicationContext.getBeansOfType(Validator.class).values()){
            if(binder.getTarget()!=null && validator.supports(binder.getTarget().getClass())){
                vas.add(validator);
            }
        }
        binder.replaceValidators(vas.toArray(new Validator[0]));
    }

}