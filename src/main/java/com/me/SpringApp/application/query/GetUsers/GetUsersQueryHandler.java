package com.me.SpringApp.application.query.GetUsers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQuery;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQueryResult;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;
import com.me.SpringApp.infra.repositories.UserRepository;

@Service
public class GetUsersQueryHandler {

    private IUserRepositoryMemory userRepositoryMemory;
    private UserRepository userRepository;

    @Autowired
    public GetUsersQueryHandler(UserRepository repository, IUserRepositoryMemory memRepo) {
        userRepository = repository;
        userRepositoryMemory = memRepo;
    }

    public GetUsersQueryResult handle(GetUsersQuery query) {
        List<User> users = userRepository.findAll();
        GetUsersQueryResult output = new GetUsersQueryResult(users);
        return output;
    }
}
