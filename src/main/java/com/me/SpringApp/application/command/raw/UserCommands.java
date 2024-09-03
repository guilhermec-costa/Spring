package com.me.SpringApp.application.command.raw;

import com.me.SpringApp.domain.User.UserRole;

public abstract class UserCommands {
	public static record CreateUserCommand(String login, String password, String email, UserRole Role) {}
	public static record DeleteUserCommand(Long id) {};
	public static record AuthenticateUserCommand(String login, String password) {};
}
