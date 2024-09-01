package com.me.SpringApp.application.command.services;

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
public class AuthorCommandService {
}
