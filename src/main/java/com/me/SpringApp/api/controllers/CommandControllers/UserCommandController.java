package com.me.SpringApp.api.controllers.CommandControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.SpringApp.application.command.dataStructures.UserCommands.DeleteUserCommand;
import com.me.SpringApp.application.command.services.UserCommandService;

@RestController
@RequestMapping("user")
public class UserCommandController {

  private final UserCommandService userCommandService;

  @Autowired
  public UserCommandController(final UserCommandService userCommandService) {
    this.userCommandService = userCommandService;
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Object> delete(@PathVariable("userId") String userId) {
    DeleteUserCommand command = new DeleteUserCommand(Long.parseLong(userId));
    userCommandService.delete(command);
    return ResponseEntity.noContent().build();
  }

}
