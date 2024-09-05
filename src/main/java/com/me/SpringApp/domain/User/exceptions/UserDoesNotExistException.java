package com.me.SpringApp.domain.User.exceptions;

public class UserDoesNotExistException extends RuntimeException {
	
	public UserDoesNotExistException(String message) {
		super(message);
	}
}
