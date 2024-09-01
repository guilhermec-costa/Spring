package com.me.SpringApp.application.query.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.query.raw.UserQueries.*;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class UserQueryService {

	private UserRepository userRepository;

	@Autowired
	public UserQueryService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(GetUserQuery query) {
		Optional<User> user = userRepository.findById(query.id());
		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	public List<User> getUsers(GetUsersQuery query) {
		List<User> users = userRepository.findAll();
		return users;
	}
}
