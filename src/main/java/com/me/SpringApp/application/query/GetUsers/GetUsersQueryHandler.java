package com.me.SpringApp.application.query.GetUsers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQuery;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQueryResult;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

public class GetUsersQueryHandler {

    @Autowired
    private IUserRepositoryMemory _userRepository;

    public GetUsersQueryResult handle(GetUsersQuery query) {
        List<User> users = _userRepository.findAll();
        GetUsersQueryResult output = new GetUsersQueryResult(users);
        return output;
    }
}
