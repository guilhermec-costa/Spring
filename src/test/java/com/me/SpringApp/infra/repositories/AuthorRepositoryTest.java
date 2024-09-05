package com.me.SpringApp.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.javafaker.Faker;
import com.me.SpringApp.domain.Author.Entity.AuthorEntity;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository _authorRepository;
	private Faker faker;

	@BeforeEach
	void setup() {
		faker = new Faker();	
	}

	@AfterEach
	void tearDown() {
			_authorRepository.deleteAll();
	}

	@Test
	@DisplayName("Shold create author, update age and get the updated age")
	void testUpdateAuthorAge() {
		final String email = faker.internet().emailAddress();
		final int ageBefore = faker.number().numberBetween(10, 90);
		final int ageAfter = ageBefore + 2;

		AuthorEntity author = new AuthorEntity("guilherme", "costa", email, ageBefore);
		_authorRepository.save(author);

		var authorBefore = _authorRepository.findByEmail(email);
		assertThat(authorBefore).isNotEmpty();

		final var idToUpdate = authorBefore.get().getId();
		_authorRepository.updateAuthorAge(idToUpdate, ageAfter);
		_authorRepository.flush();

		var authorAfter = _authorRepository.findByEmail(email);

		assertThat(authorAfter).isNotEmpty();
		assertThat(authorAfter.get().getAge()).isNotEqualTo(ageBefore);
		assertThat(authorBefore.get().getAge()).isNotEqualTo(ageAfter);
	}
}
