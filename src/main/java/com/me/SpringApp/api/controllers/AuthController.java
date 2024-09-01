package com.me.SpringApp.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommand;
import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommandHandler;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.exceptions.CustomException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CreateUserCommandHandler _createCreateUserCommandHandler;

    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody User payload) {
        CreateUserCommand command = new CreateUserCommand(payload.getName(), payload.getPassword(), payload.getEmail());
        var result = _createCreateUserCommandHandler.handle(command);

        return ResponseEntity.status(201).body(result);
    };

    @PostMapping("/exception")
    public ResponseEntity throwError() {
        _createCreateUserCommandHandler.takeRisk();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
