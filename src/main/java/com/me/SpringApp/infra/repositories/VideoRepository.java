package com.me.SpringApp.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.SpringApp.domain.entities.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
	
}
