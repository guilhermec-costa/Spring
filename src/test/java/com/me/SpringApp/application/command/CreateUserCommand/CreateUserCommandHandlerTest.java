package com.me.SpringApp.application.command.CreateUserCommand;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.me.SpringApp.application.service.RandomService;
import com.me.SpringApp.infra.repositories.UserRepository;

public class CreateUserCommandHandlerTest {

    @Mock
    private RandomService randomService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should create user")
    void testHandle() {
        when(randomService.randomize()).thenReturn(5);
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
}
