package com.liuyf.demos.spring.akka.integration.example.service;

import org.springframework.stereotype.Component;

/**
 * Created by tony on 2017/7/3.
 */
@Component
public class GreetingService {

    public String greet(String name) {

        return "Hello, " + name;
    }
}
