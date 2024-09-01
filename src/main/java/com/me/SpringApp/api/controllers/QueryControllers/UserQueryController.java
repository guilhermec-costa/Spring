package com.me.SpringApp.api.controllers.QueryControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.query.raw.UserQueries;
import com.me.SpringApp.application.query.services.UserQueryService;
import com.me.SpringApp.domain.entities.User;

@RestController
@RequestMapping("user")
public class UserQueryController {

	private final UserQueryService userQueryService;

	@Autowired
	public UserQueryController(final UserQueryService userQueryService) {
		this.userQueryService = userQueryService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") String id) {
		var query = new UserQueries.GetUserQuery(Long.parseLong(id));
		var user = userQueryService.getUser(query);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<User>> findAll() {
		var query = new UserQueries.GetUsersQuery();
		var result = userQueryService.getUsers(query);
		return new ResponseEntity<List<User>>(result, HttpStatus.OK);
	}

}
