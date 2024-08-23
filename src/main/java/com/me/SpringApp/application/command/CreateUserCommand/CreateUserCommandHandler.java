package com.me.SpringApp.application.command.CreateUserCommand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

@Service
public class CreateUserCommandHandler {

    private IUserRepositoryMemory _userRepository;

    public CreateUserCommandHandler(IUserRepositoryMemory repository)
    {
        _userRepository = repository;
    }

    public void handle(CreateUserCommand command)
    {
        User user = new User();
        user.setName(command.name());
        user.setEmail(command.email());
        user.setPassword(command.password());
        _userRepository.insert(user);
    }

    public List<User> getAll()
    {
        return _userRepository.findAll();
    }
}