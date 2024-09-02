package com.me.SpringApp.application.command.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.command.raw.UserCommands.*;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class UserCommandService {

	private UserRepository userRepository;

	@Autowired
	public UserCommandService(UserRepository _userRepository) {
		userRepository = _userRepository;
	}

	public User create(CreateUserCommand command) {
		Optional<User> existingUser = userRepository.getUserByEmail(command.email());
		if (existingUser.isPresent()) {
			throw new IllegalStateException("Email already taken");
		}

		User user = new User();
		user.setLogin(command.name());
		user.setEmail(command.email());
		user.setPassword(command.password());
		userRepository.save(user);
		return user;
	}

	public DeleteUserCommand delete(DeleteUserCommand command) {
		final Long id = command.id();
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		}
		return command;
	}
}
