package com.me.SpringApp.application.query.dataStructures;

import com.me.SpringApp.application.abstractions.Query;

public abstract class UserQueries {
	
	public static record GetUserQuery(Long id) implements Query {}
	public static record GetUsersQuery() implements Query {};
}
