package com.me.SpringApp.application.query.GetUser;

public interface IGetUserQuery {

    public record GetUserQueryResult(
            String name,
            String email) {};

    public record GetUserQuery(Long id) {
    };
}
