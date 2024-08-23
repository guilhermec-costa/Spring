package com.me.SpringApp.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommand;
import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommandHandler;
import com.me.SpringApp.domain.entities.User;

@RestController
// @Controller
// @ResponseBody
@RequestMapping("/auth")
public class AuthController {

    @PostMapping()
    public ResponseEntity<User> signin(@RequestBody User payload) {
        CreateUserCommand command = new CreateUserCommand(
            payload.getName(),
            payload.getPassword(),
            payload.getPassword()
        );
        CreateUserCommandHandler handler = new CreateUserCommandHandler();
        var result = handler.handle(command);

        return ResponseEntity.status(201).body(result);
    };
}
