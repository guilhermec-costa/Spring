package com.me.SpringApp.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.entities.Author;

import jakarta.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	List<Author> findByNamedQuery(@Param("age") int age);

	// get queries
	List<Author> findAllByFirstName(String fn);

	List<Author> findAllByFirstNameIgnoreCase(String fn);

	List<Author> findAllByFirstNameContainingIgnoreCase(String fn);

	List<Author> findAllByFirstNameStartsWith(String str);

	List<Author> findAllByFirstNameEndsWith(String str);

	List<Author> findAllByFirstNameInIgnoreCase(List<String> strs);

	// update queries
	@Modifying
	@Transactional
	@Query("update Author a set a.age = :age where a.id = :id")
	int updateAuthorAge(int id, int age);
}
