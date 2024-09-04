package com.me.SpringApp.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.domain.User.UserRole;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository _userRepository;

    @AfterEach
    void tearDown() {
        _userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create user and get the user by email")
    void itShouldGetUserByEmail() {
        String email = "echina123@gmail.com";
        User user = new User(
            "guilherme.china.morais", 
            email, 
            "123456", 
            UserRole.ADMIN
        );
         _userRepository.save(user);

         Optional<User> createdUser = _userRepository.findByEmail(email);
         assertThat(createdUser).isNotEmpty();
         assertThat(createdUser.get().getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("Should create user and do not find user by email")
    void itShouldNotFindUserByEmail() {
        String email = "echina123@gmail.com";
        User user = new User(
            "guilherme.china.morais", 
            email, 
            "123456", 
            UserRole.ADMIN
        );
         _userRepository.save(user);

         String invalidEmail = email + "invalid";

         Optional<User> createdUser = _userRepository.findByEmail(invalidEmail);
         assertThat(createdUser).isEmpty();
    }
}