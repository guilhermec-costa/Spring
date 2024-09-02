package com.me.SpringApp.api.controllers.CommandControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.raw.UserCommands.*;
import com.me.SpringApp.application.command.services.UserCommandService;
import com.me.SpringApp.domain.User.User;

@RestController
@RequestMapping("auth")
public class AuthCommandController {

	private final UserCommandService userCommandService;

	@Autowired
	public AuthCommandController(final UserCommandService userCommandService) {
		this.userCommandService = userCommandService;
	}

	@PostMapping("/signin")
	public ResponseEntity<User> signin(@RequestBody User payload) {
		CreateUserCommand command = new CreateUserCommand(payload.getLogin(), payload.getPassword(), payload.getEmail());
		var result = userCommandService.create(command);
		return ResponseEntity.status(201).body(result);
	};
}
