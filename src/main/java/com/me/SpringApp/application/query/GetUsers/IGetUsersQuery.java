package com.me.SpringApp.application.query.GetUsers;

import java.util.List;

import com.me.SpringApp.domain.entities.User;

public interface IGetUsersQuery {

    public record GetUsersQueryResult(List<User> users) {};

    public record GetUsersQuery() {};

}
