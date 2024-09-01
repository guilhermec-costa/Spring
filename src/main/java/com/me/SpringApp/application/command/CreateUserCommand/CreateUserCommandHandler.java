package com.me.SpringApp.application.command.CreateUserCommand;

import java.util.List;
import java.lang.IllegalStateException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.exceptions.CustomException;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class CreateUserCommandHandler {

    private UserRepository userRepository;

    @Autowired
    public CreateUserCommandHandler(UserRepository _userRepository) {
        userRepository = _userRepository;
    }

    public User handle(CreateUserCommand command) {
        Optional<User> existingUser = userRepository.getUserByEmail(command.email());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        User user = new User();
        user.setName(command.name());
        user.setEmail(command.email());
        user.setPassword(command.password());
        userRepository.save(user);
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void takeRisk() {
        throw new CustomException("Something has gone wrong");
    }
}