package com.me.SpringApp.application.command.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static com.me.SpringApp.utils.UserUtils.generateUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.github.javafaker.Faker;
import com.me.SpringApp.application.abstractions.TokenService;
import com.me.SpringApp.application.command.dataStructures.UserCommands.*;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.domain.User.exceptions.*;
import com.me.SpringApp.infra.repositories.UserRepository;
import java.util.Optional;
import org.springframework.security.core.Authentication; 

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {

	@Mock private UserRepository _userRepository;
	@Mock private AuthenticationManager _authenticationManager;
	@Mock private TokenService tokenService;

	private UserCommandService _userCommandService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		_userCommandService = new UserCommandService(_userRepository, _authenticationManager, tokenService);
	}

	@Test
	void shouldDeleteUser() {
		var user = generateUser();
		given(_userRepository.findById(anyLong())).willReturn(Optional.of(user));
		DeleteUserCommand deletionCommand = new DeleteUserCommand(1L);
		_userCommandService.delete(deletionCommand);
		verify(_userRepository, times(1)).findById(deletionCommand.id());
	}

	@Test
	void shouldLoginUser() {
		String expectedToken = "expected-jwt-token";
		User user = generateUser();
		AuthenticateUserCommand authenticateCommand = new AuthenticateUserCommand(user.getLogin(), user.getPassword());
		Authentication auth = mock(Authentication.class);

		when(auth.getPrincipal()).thenReturn(user);
		when(_authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
			.thenReturn(auth);

		when(tokenService.generateToken(user)).thenReturn(expectedToken);

		String jwt = _userCommandService.login(authenticateCommand);

		assertEquals(expectedToken, jwt);
		verify(_authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
		verify(tokenService, times(1)).generateToken(user);
	}

	@Test
	void shouldRegisterUser() {
		User user = generateUser();
		CreateUserCommand creationCommand = new CreateUserCommand(user.getLogin(), user.getPassword(), user.getEmail(),
				user.getRole());
		_userCommandService.register(creationCommand);

		verify(_userRepository).findByLogin(creationCommand.login());
	}

	@Test
	void shouldRegisterUserAndThrowDuplicateUserException() {
		User user1 = generateUser();
		CreateUserCommand creationCommand1 = new CreateUserCommand(user1.getLogin(), user1.getPassword(), user1.getEmail(),
				user1.getRole());

		when(_userRepository.findByLogin(user1.getLogin())).thenReturn(null);
		_userCommandService.register(creationCommand1);
	}

	@Test
	void willThrowWhenLoginIsTaken() {
		User user = generateUser();
		CreateUserCommand command = new CreateUserCommand(user.getLogin(), user.getPassword(), user.getEmail(),
				user.getRole());
		given(_userRepository.findByLogin(anyString())).willReturn(user);

		assertThatThrownBy(() -> _userCommandService.register(command)).isInstanceOf(DuplicateUserException.class);
		verify(_userRepository, never()).save(any());
	}

	@Test
	void willThrowWhenUserDoesNotExistOnDeletion() {
		DeleteUserCommand command = new DeleteUserCommand(1L);
		given(_userRepository.findById(anyLong())).willReturn(Optional.empty());
		assertThatThrownBy(() -> _userCommandService.delete(command)).isInstanceOf(UserDoesNotExistException.class);
		assertThrows(UserDoesNotExistException.class, () -> _userCommandService.delete(command));
	}
}
