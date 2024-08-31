package com.me.SpringApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.me.SpringApp.domain.entities.Author;
import com.me.SpringApp.domain.entities.Video;
import com.me.SpringApp.infra.repositories.AuthorRepository;
import com.me.SpringApp.infra.repositories.VideoRepository;

// entry point
// @Service, @Controller, @Repository são especializações de @Component
// o que significa que o próprio Spring gerencia a D.I das classes anotadas com eles
@SpringBootApplication
public class SpringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		AuthorRepository authorRepository,
		VideoRepository videoRepository
	)
	{
		System.out.println("starting programming");
		return args -> {
			Author author = Author.builder()
				.firstName("guilherme costa")
				.lastName("costa")
				.age(20)
				.email("echina725@gmail.com")
				.build();

				Video video = Video.builder()
					.name("guilherm video")
					.length(12)
					.build();

				authorRepository.save(author);
				videoRepository.save(video);
		};
	}
}
