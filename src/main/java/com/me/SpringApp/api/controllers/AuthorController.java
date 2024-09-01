package com.me.SpringApp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.AuthorCommandService;
import com.me.SpringApp.domain.Author.Entity.AuthorEntity;

@RequestMapping("authors")
@RestController
public class AuthorController {

	private final AuthorCommandService authorCommandService;

	@Autowired
	public AuthorController(final AuthorCommandService commandService) {
		this.authorCommandService = commandService;
	}

	@GetMapping()
	public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
		return authorCommandService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorEntity> getAuthor(@PathVariable("id") String id) {
		return authorCommandService.getAuthorById(id);
	}
}
