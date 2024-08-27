package com.me.SpringApp.application.command.DeleteUserCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class DeleteUserCommandHandler {

    private final UserRepository _userRepository;

    @Autowired
    DeleteUserCommandHandler(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public DeleteUserCommand handle(DeleteUserCommand command) {
        final Long id = command.id();
        Optional<User> user = _userRepository.findById(id);
        if (user.isPresent()) {
            _userRepository.delete(user.get());
        }
        return command;
    }
}
