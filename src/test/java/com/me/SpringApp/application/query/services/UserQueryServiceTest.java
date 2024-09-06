package com.me.SpringApp.application.query.services;

import static com.me.SpringApp.utils.UserUtils.generateUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.me.SpringApp.application.query.dataStructures.UserQueries.GetUserQuery;
import com.me.SpringApp.application.query.dataStructures.UserQueries.GetUsersQuery;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.infra.repositories.UserRepository;

public class UserQueryServiceTest {

	@Mock private UserRepository _userRepository;
	private UserQueryService _userQueryService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		_userQueryService = new UserQueryService(_userRepository);
	}

	@Test
	void testGetUser() {
		User user = generateUser();
		GetUserQuery userQuery = new GetUserQuery(1L);
		when(_userRepository.findById(anyLong())).thenReturn(Optional.of(user));

		// assertThat(foundedUser).isEqualTo(user);
		// verify(_userRepository, times(1)).findById(anyLong());
	}

	@Test
	void testGetUsers() {
		GetUsersQuery usersQuery = new GetUsersQuery();
		when(_userRepository.findAll());
	}
}
