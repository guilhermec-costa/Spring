package com.me.SpringApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.service.RandomService;
import com.me.SpringApp.application.service.StringService;
import com.me.SpringApp.application.usecase.Login;

@RestController
// @Controller
// @ResponseBody
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StringService stringService;

    private RandomService randomService;
    public AuthController(RandomService randomService) {
        this.randomService = randomService;
    }

    

    @PostMapping()
    public String login() {
        Login LoginCase = new Login();
        String password = randomService.randomize().toString();
        String login = "axqwRR1212";
        var result = LoginCase.execute(login, password);
        return result;
    }
}
