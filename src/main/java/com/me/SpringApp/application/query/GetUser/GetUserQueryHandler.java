package com.me.SpringApp.application.query.GetUser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.me.SpringApp.application.query.GetUser.IGetUserQuery.*;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

public class GetUserQueryHandler {

    @Autowired
    private IUserRepositoryMemory _userRepository;

    public GetUserQueryResult handle(GetUserQuery query) {
        Optional<User> user = _userRepository.findById(query.id());
        if (user.isPresent()) {
            User rawUser = user.get();
            return new GetUserQueryResult(
                    rawUser.getName(),
                    rawUser.getEmail());
        }

        return new GetUserQueryResult(null, null);

    }
}
