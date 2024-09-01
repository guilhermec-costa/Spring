package com.me.SpringApp.application.query.raw;

public abstract class UserQueries {
	
	public static record GetUserQuery(Long id) {}
	public static record GetUsersQuery() {};
}
