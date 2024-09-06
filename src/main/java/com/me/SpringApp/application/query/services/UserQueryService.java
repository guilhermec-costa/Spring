package com.me.SpringApp.application.query.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.abstractions.BaseQueryService;
import com.me.SpringApp.application.abstractions.Query;
import com.me.SpringApp.application.query.dataStructures.UserQueries.*;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class UserQueryService implements BaseQueryService<User> {

	private UserRepository userRepository;

	@Autowired
	public UserQueryService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll(Query query) {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public Optional<User> findOne(Query query) {
		var convertedQuery = (GetUserQuery) query;
		Optional<User> user = userRepository.findById(convertedQuery.id());
		return user;
	}

}
