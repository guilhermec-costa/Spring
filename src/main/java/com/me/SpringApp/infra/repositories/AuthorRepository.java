package com.me.SpringApp.infra.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.Author.Entity.AuthorEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

	List<AuthorEntity> findByNamedQuery(@Param("age") int age);

	// get queries
	List<AuthorEntity> findAllByFirstName(String fn);

	List<AuthorEntity> findAllByFirstNameIgnoreCase(String fn);

	List<AuthorEntity> findAllByFirstNameContainingIgnoreCase(String fn);

	List<AuthorEntity> findAllByFirstNameStartsWith(String str);

	List<AuthorEntity> findAllByFirstNameEndsWith(String str);

	List<AuthorEntity> findAllByFirstNameInIgnoreCase(List<String> strs);

	Optional<AuthorEntity> findByEmail(String email);

	// update queries
	@Modifying
	@Query("update AuthorEntity a set a.age = ?2 where a.id = ?1")
	int updateAuthorAge(@Param("id") Long id, @Param("age") int age);
}
