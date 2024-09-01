package com.me.SpringApp.domain.Author.Exceptions;

public class AuthorDoesNotExistException extends RuntimeException {

	public AuthorDoesNotExistException(String message) {
		super(message);
	}

	public AuthorDoesNotExistException(Throwable cause) {
		super(cause);
	}
}
