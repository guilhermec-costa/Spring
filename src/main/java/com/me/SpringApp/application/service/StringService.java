package com.me.SpringApp.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StringService {

    @Autowired
    RandomService randomService;

    public String generate(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int c = randomService.randomize();
            char character = (char)c;
            System.out.println(character);
            builder.append(character);
        }
        return builder.toString();
    }
}
