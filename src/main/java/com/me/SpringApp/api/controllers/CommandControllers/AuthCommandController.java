package com.me.SpringApp.api.controllers.CommandControllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
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
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthCommandController(
		final UserCommandService userCommandService,
		final AuthenticationManager authenticationManager) {
		this.userCommandService = userCommandService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticateUserCommand data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/signin")
	public ResponseEntity<User> signin(@RequestBody User payload) {
		CreateUserCommand command = new CreateUserCommand(payload.getLogin(), payload.getPassword(), payload.getEmail(), payload.getRole());
		var result = userCommandService.create(command);
		return ResponseEntity.status(201).body(result);
	};

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody CreateUserCommand payload) {
		CreateUserCommand command = new CreateUserCommand(payload.login(), payload.password(), payload.email(), payload.Role());
		return userCommandService.register(command);
	}
}
