package com.me.SpringApp;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommand;
import com.me.SpringApp.application.command.CreateUserCommand.CreateUserCommandHandler;
import com.me.SpringApp.domain.entities.User;
import com.me.SpringApp.infra.repositories.UserRepository;
import com.me.SpringApp.infra.repositories.UserRepositoryMemoryImpl;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = SpringAppApplication.class)
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully when queried by email")
    void findUserByEmailSuccess() {
        CreateUserCommand command = new CreateUserCommand("guilherme costa", "123456", "echina725@gmail.com");
        final User user = this.createUser(command);

        Optional<User> userCreated = userRepository.findUserByEmail(user.getEmail());
        assertThat(userCreated.isPresent()).isTrue();
        assertThat(userCreated.get().getEmail()).isEqualTo(user.getEmail());
    }

    private User createUser(CreateUserCommand command) {
        CreateUserCommandHandler handler = new CreateUserCommandHandler(userRepository, new UserRepositoryMemoryImpl());
        final User user = handler.handle(command);
        this.entityManager.persist(user);
        return user;
    }
}
