package com.me.SpringApp.application.query.GetUser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.query.GetUser.IGetUserQuery.*;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class GetUserQueryHandler {

    private IUserRepositoryMemory memoryRepository;
    private UserRepository userRepository;

    @Autowired
    public GetUserQueryHandler(IUserRepositoryMemory _repositoryMemory, UserRepository _userRepository)
    {
        userRepository = _userRepository;
        memoryRepository = _repositoryMemory;
    }

    public GetUserQueryResult handle(GetUserQuery query) {
        Optional<User> user = userRepository.findById(query.id());
        if (user.isPresent()) {
            User rawUser = user.get();
            return new GetUserQueryResult(
                    rawUser.getName(),
                    rawUser.getEmail());
        }

        return new GetUserQueryResult(null, null);

    }
}
