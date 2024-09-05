package com.me.SpringApp.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.me.SpringApp.domain.Author.Entity.AuthorEntity;


@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository _authorRepository;

	@Test
	@DisplayName("Shold create author, update age and get the updated age")
	@Disabled
	void testUpdateAuthorAge() {
		final String email = "echina725@gmail.com";
		final int ageBefore = 20;
		final int ageAfter = 22;
		AuthorEntity author = new AuthorEntity("guilherme", "costa", email, ageBefore);
		_authorRepository.save(author);

		var authorBefore = _authorRepository.findByEmail(email);
		_authorRepository.updateAuthorAge(authorBefore.get().getId(), ageAfter);
		var authorAfter = _authorRepository.findByEmail(email);

		assertThat(authorBefore).isNotEmpty();
		assertThat(authorAfter).isNotEmpty();

		assertThat(authorAfter.get().getAge()).isNotEqualTo(ageBefore);
		assertThat(authorBefore.get().getAge()).isNotEqualTo(ageAfter);

		assertThat(authorBefore.get().getAge()).isEqualTo(ageBefore);
		assertThat(authorAfter.get().getAge()).isEqualTo(ageAfter);
	}
}
