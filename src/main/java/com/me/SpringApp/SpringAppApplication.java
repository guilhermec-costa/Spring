package com.me.SpringApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

// entry point
@SpringBootApplication
@Profile("dev")
public class SpringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

}
