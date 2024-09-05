package com.me.SpringApp.application.command.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.abstractions.TokenService;
import com.me.SpringApp.application.command.dataStructures.UserCommands.AuthenticateUserCommand;
import com.me.SpringApp.application.command.dataStructures.UserCommands.CreateUserCommand;
import com.me.SpringApp.application.command.dataStructures.UserCommands.DeleteUserCommand;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.domain.User.UserRole;
import com.me.SpringApp.domain.User.exceptions.*;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class UserCommandService {

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Autowired
	public UserCommandService(final UserRepository userRepository, final AuthenticationManager authenticationManager,
			final TokenService tokenService) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	public String login(final AuthenticateUserCommand command) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(command.login(), command.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		final var userAuthenticating = (User) auth.getPrincipal();
		String token = tokenService.generateToken(userAuthenticating);
		return token;
	}

	public void register(CreateUserCommand command) {
		var existingUser = this.userRepository.findByLogin(command.login());
		if (existingUser != null)
			throw new DuplicateUserException("User already exists");

		String encryptedPasswod = new BCryptPasswordEncoder().encode(command.password());
		User newUser = new User(command.login(), command.email(), encryptedPasswod, UserRole.ADMIN);

		userRepository.save(newUser);
	}

	public void delete(DeleteUserCommand command) {
		final Long id = command.id();
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserDoesNotExistException("User does not exist");
		}

		userRepository.delete(user.get());
	}
}
