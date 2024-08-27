package com.me.SpringApp.api.controllers;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.DeleteUserCommand.DeleteUserCommand;
import com.me.SpringApp.application.command.DeleteUserCommand.DeleteUserCommandHandler;
import com.me.SpringApp.application.query.GetUser.GetUserQueryHandler;
import com.me.SpringApp.application.query.GetUser.IGetUserQuery.*;
import com.me.SpringApp.application.query.GetUsers.GetUsersQueryHandler;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQuery;
import com.me.SpringApp.application.query.GetUsers.IGetUsersQuery.GetUsersQueryResult;

@RestController
@RequestMapping("/user")
public class UserController {

    private final GetUserQueryHandler _GetUserQueryHandler;
    private final GetUsersQueryHandler _GetUsersQueryHandler;
    private final DeleteUserCommandHandler _DeleteUserCommandHandler;

    @Autowired
    public UserController(
            GetUserQueryHandler userQueryHandler,
            GetUsersQueryHandler usersQueryHandler,
            DeleteUserCommandHandler deleteUserCommandHandler) {
        this._GetUserQueryHandler = userQueryHandler;
        this._GetUsersQueryHandler = usersQueryHandler;
        this._DeleteUserCommandHandler = deleteUserCommandHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserQueryResult> findById(
            @PathVariable("id") String id) {
        GetUserQuery query = new GetUserQuery(Long.parseLong(id));
        var result = _GetUserQueryHandler.handle(query);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<GetUsersQueryResult> findAll() {
        GetUsersQuery query = new GetUsersQuery();
        var result = _GetUsersQueryHandler.handle(query);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteUserCommand> delete(@PathVariable("userId") String userId) {
        DeleteUserCommand command = new DeleteUserCommand(Long.parseLong(userId));
        DeleteUserCommand commandResponse = _DeleteUserCommandHandler.handle(command);
        return ResponseEntity.ok(commandResponse);
    }

}
