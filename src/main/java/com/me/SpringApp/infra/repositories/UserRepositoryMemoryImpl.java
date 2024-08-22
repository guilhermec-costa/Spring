package com.me.SpringApp.infra.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

@Repository
public class UserRepositoryMemoryImpl<User> implements IUserRepositoryMemory<User> {

    private ArrayList<User> users;

    UserRepositoryMemoryImpl() {
        this.users = new ArrayList<User>();
    }

    public void insert(User user) {
        this.users.add(user);
    }
}
