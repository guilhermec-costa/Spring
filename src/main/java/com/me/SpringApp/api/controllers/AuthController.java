package com.me.SpringApp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommand;
import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommandHandler;
import com.me.SpringApp.application.service.RandomService;
import com.me.SpringApp.application.service.StringService;
import com.me.SpringApp.application.usecase.Login;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;
import com.me.SpringApp.infra.repositories.UserRepositoryMemoryImpl;

@RestController
// @Controller
// @ResponseBody
@RequestMapping("/auth")
public class AuthController {

    // auto injection.
    @Autowired
    private StringService stringService;

    @Autowired
    private IUserRepositoryMemory userRepositoryMemory;

    // no autowide, so it is need to instantiate via constructor
    private RandomService randomService;

    public AuthController(RandomService randomService) {
        this.randomService = randomService;
    }

    @PostMapping()
    public void login(@RequestBody User payload) {
        String password = randomService.randomize().toString();
        String login = "axqwRR1212";
        CreateUserCommand command = new CreateUserCommand(login, password, "echina725@gmail.com");
        CreateUserCommandHandler handler = new CreateUserCommandHandler(userRepositoryMemory);
        handler.handle(command);
    };

    @GetMapping("/users")
    public List<User> findAll()
    {
        return userRepositoryMemory.findAll();
    }

    @PostMapping("/{id}")
    public String getId(
            @PathVariable("id") String id,
            @RequestParam("name") String name,
            @RequestParam(value = "age", defaultValue = "18") String age) {
        return "id: " + name + " " + age;
    }
}
