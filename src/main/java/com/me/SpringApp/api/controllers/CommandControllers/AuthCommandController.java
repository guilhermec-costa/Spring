package com.me.SpringApp.api.controllers.CommandControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.dataStructures.UserCommands.AuthenticateUserCommand;
import com.me.SpringApp.application.command.dataStructures.UserCommands.CreateUserCommand;
import com.me.SpringApp.application.command.services.UserCommandService;

@RestController
@RequestMapping("auth")
public class AuthCommandController {

	private final UserCommandService userCommandService;

	@Autowired
	public AuthCommandController(final UserCommandService userCommandService) {
		this.userCommandService = userCommandService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Validated @RequestBody AuthenticateUserCommand data) {
		var command = new AuthenticateUserCommand(data.login(), data.password());
		String token = userCommandService.login(command);

		return ResponseEntity.ok(token);
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@Validated @RequestBody CreateUserCommand payload) {
		CreateUserCommand createCommand = new CreateUserCommand(
			payload.login(), 
			payload.password(), 
			payload.email(),
			payload.Role()
		);
		userCommandService.register(createCommand);

		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}
}
