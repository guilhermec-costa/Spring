package com.me.SpringApp.infra.repositories;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.me.SpringApp.domain.User.User;

import static org.assertj.core.api.Assertions.*;

import jakarta.persistence.EntityManager;

@DataJpaTest(showSql = true)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Should get user successfully when queried by email")
    void findUserByEmailSuccess() {
        // User user = new User("guilherme china", "echina725@gmail.com", "123");
        // userRepository.save(user);

        // Optional<User> userCreated = userRepository.findByEmail(user.getEmail());
        // assertThat(userCreated.isPresent()).isTrue();
        // assertThat(userCreated.get().getEmail()).isEqualTo(user.getEmail());
    }
}