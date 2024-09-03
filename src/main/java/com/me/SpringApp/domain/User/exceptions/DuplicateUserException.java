package com.me.SpringApp.domain.User.exceptions;

public class DuplicateUserException extends RuntimeException {
	
	public DuplicateUserException(String message) {
		super(message);
	}
}
