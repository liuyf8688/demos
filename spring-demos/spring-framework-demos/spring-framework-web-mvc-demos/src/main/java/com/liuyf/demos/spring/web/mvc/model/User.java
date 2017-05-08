package com.liuyf.demos.spring.web.mvc.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public interface userJsonView { }
    
    @NotNull
    private String name;
    
    @NotNull
    private String phone;

    @JsonView(userJsonView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(userJsonView.class)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
