package com.me.SpringApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.me.SpringApp.domain.entities.Author;
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
	public CommandLineRunner commandLineRunner(AuthorRepository authorRepository, VideoRepository videoRepository) {
		System.out.println("starting programming");
		return args -> {
			// authorRepository.updateAuthorAge(2, 150);
			// Faker faker = new Faker();
			// for(int i=0; i< 50; ++i)
			// {
			// var author = Author.builder()
			// .firstName(faker.name().firstName())
			// .lastName(faker.name().lastName())
			// .age(faker.number().numberBetween(10, 100))
			// .email(faker.internet().emailAddress())
			// .build();

			// authorRepository.save(author);
			// }
			var authors = authorRepository.findByNamedQuery(50);
			authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
			// authorRepository.findByAge(50)
			// .forEach(System.out::println);
		};
	}
}
