package com.me.SpringApp.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
