package com.me.SpringApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

// entry point
// @Service, @Controller, @Repository são especializações de @Component
// o que significa que o próprio Spring gerencia a D.I das classes anotadas com eles
@SpringBootApplication
@Profile("dev")
public class SpringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}
}
