package com.me.SpringApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.query.GetUser.GetUserQueryHandler;
import com.me.SpringApp.application.query.GetUser.IGetUserQuery.*;
import com.me.SpringApp.application.query.GetUsers.GetUsersQueryHandler;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQuery;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQueryResult;
import com.me.SpringApp.domain.repositories.IUserRepositoryMemory;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<GetUserQueryResult> findById(
            @PathVariable("id") String id) {
        GetUserQuery query = new GetUserQuery(Long.parseLong(id));
        GetUserQueryHandler handler = new GetUserQueryHandler();
        var result = handler.handle(query);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<GetUsersQueryResult> findAll() {
        GetUsersQuery query = new GetUsersQuery();
        GetUsersQueryHandler handler = new GetUsersQueryHandler();
        var result = handler.handle(query);
        return ResponseEntity.ok(result);
    }

}
