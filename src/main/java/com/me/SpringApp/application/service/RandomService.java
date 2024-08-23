package com.me.SpringApp.application.service;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

    public Integer randomize() {
        int x = (int)(Math.random() * 10);
        return x;
    }
}
