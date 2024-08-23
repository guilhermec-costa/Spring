package com.me.SpringApp.infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

@Repository
public class UserRepositoryMemoryImpl implements IUserRepositoryMemory {

    private final List<User> users = new ArrayList<>();

    @Override
    public void insert(User user) {
        users.add(user);
    }

    @Override
    public void update(Long id, User user)
    {
        int idx = Long.valueOf(id).intValue();
        users.set(idx, user);
    }

    @Override
    public void delete(Long id)
    {
        int idx = Long.valueOf(id).intValue();
        users.remove(idx);
    }

    @Override
    public List<User> findAll()
    {
        return users;
    }

    @Override
    public Optional<User> findById(Long id)
    {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
