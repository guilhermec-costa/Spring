package com.me.SpringApp.utils;

import com.github.javafaker.Faker;
import com.me.SpringApp.domain.User.User;
import com.me.SpringApp.domain.User.UserRole;

public class UserUtils {

	private static Faker faker;

	static {
		faker = new Faker();
	}

	public static User generateUser() {
		User user = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(5, 15),
				UserRole.ADMIN);

		return user;
	}
}
