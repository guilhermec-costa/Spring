package com.me.SpringApp.api.controllers.QueryControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.query.services.AuthorQueryService;
import com.me.SpringApp.domain.Author.Entity.AuthorEntity;

@RestController
@RequestMapping("authors")
public class AuthorQueryController {

	private final AuthorQueryService authorQueryService;

	@Autowired
	public AuthorQueryController(final AuthorQueryService authorQueryService) {
		this.authorQueryService = authorQueryService;
	}

	@GetMapping
	public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
		return authorQueryService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorEntity> getAuthor(@PathVariable("id") String id) {
		return authorQueryService.getAuthorById(id);
	}
}
