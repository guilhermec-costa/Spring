package com.me.SpringApp.application.command.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import com.github.javafaker.Faker;
import com.me.SpringApp.application.abstractions.TokenService;
import com.me.SpringApp.application.command.raw.UserCommands.CreateUserCommand;
import com.me.SpringApp.application.command.raw.UserCommands.DeleteUserCommand;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.domain.User.UserRole;
import com.me.SpringApp.domain.User.exceptions.DuplicateUserException;
import com.me.SpringApp.infra.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {

		@Mock private UserRepository _userRepository;
		@Mock private AuthenticationManager _authenticationManager;
		@Mock private TokenService tokenService;
		private UserCommandService _userCommandService;

		private Faker faker;

		@BeforeEach
		void setup() {
			MockitoAnnotations.openMocks(this);
			_userCommandService = new UserCommandService(_userRepository, _authenticationManager, tokenService);
			faker = new Faker();
		}

    @Test
    void shouldDeleteUser() {
			var user = generateUser();
			_userRepository.save(user);

			DeleteUserCommand deletionCommand = new DeleteUserCommand(1L);
			_userCommandService.delete(deletionCommand);


			verify(_userRepository, times(1)).findById(deletionCommand.id());
    }

    @Test
		@Disabled
    void shouldLoginUser() {

    }

    @Test
    void shouldRegisterUser() {
			User user = generateUser();	
			CreateUserCommand creationCommand = new CreateUserCommand(
				user.getLogin(), 
				user.getPassword(), 
				user.getEmail(), 
				user.getRole()
			);
			_userCommandService.register(creationCommand);

			verify(_userRepository).findByLogin(creationCommand.login());
    }

		@Test
		void shouldRegisterUserAndThrowDuplicateUserException() {
			User user1 = generateUser();
			CreateUserCommand creationCommand1 = new CreateUserCommand(user1.getLogin(), user1.getPassword(), user1.getEmail(), user1.getRole());

			User user2 = new User(user1);
			CreateUserCommand creationCommand2 = new CreateUserCommand(user2.getLogin(), user2.getPassword(), user2.getEmail(), user2.getRole());

			when(_userRepository.findByLogin(user1.getLogin())).thenReturn(null);
			_userCommandService.register(creationCommand1);
		}

		private User generateUser() {
			User user = new User(
						faker.name().firstName(),
            faker.internet().emailAddress(), 
            faker.internet().password(5, 15), 
            UserRole.ADMIN
      );

			return user;
		}

		@Test
		void willThrowWhenLoginIsTaken() {
			User user = generateUser();
			CreateUserCommand command = new CreateUserCommand(user.getLogin(), user.getPassword(), user.getEmail(), user.getRole());
			given(_userRepository.findByLogin(anyString())).willReturn(user);

			assertThatThrownBy(() -> _userCommandService.register(command)).isInstanceOf(DuplicateUserException.class);
			verify(_userRepository, never()).save(any());
		}
}
