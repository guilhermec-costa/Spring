package com.me.SpringApp.application.query.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.me.SpringApp.domain.Author.Entity.AuthorEntity;
import com.me.SpringApp.domain.Author.Exceptions.AuthorDoesNotExistException;
import com.me.SpringApp.infra.repositories.AuthorRepository;

@Service
public class AuthorQueryService {

	private final AuthorRepository authorRepository;

	@Autowired
	public AuthorQueryService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public ResponseEntity<AuthorEntity> getAuthorById(String id) {
		final var parsedId = Long.parseLong(id);
		Optional<AuthorEntity> author = authorRepository.findById(parsedId);
		if (author.isEmpty())
			throw new AuthorDoesNotExistException("Author does not exist");
		return new ResponseEntity<AuthorEntity>(author.get(), HttpStatus.OK);
	}

	public ResponseEntity<List<AuthorEntity>> getAll() {
		final var authors = authorRepository.findAll();
		return new ResponseEntity<List<AuthorEntity>>(authors, HttpStatus.OK);
	}
}
