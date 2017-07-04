package com.liuyf.demos.spring.akka.integration.official.service;

import org.springframework.stereotype.Component;

/**
 * Created by tony on 2017/7/4.
 */
@Component
public class CountingService {

    public int increment(int count) {

        return count + 1;
    }
}
