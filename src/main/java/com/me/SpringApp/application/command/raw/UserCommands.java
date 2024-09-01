package com.me.SpringApp.application.command.raw;

public abstract class UserCommands {
	public static record CreateUserCommand(String name, String password, String email) {}
	public static record DeleteUserCommand(Long id) {};
}
