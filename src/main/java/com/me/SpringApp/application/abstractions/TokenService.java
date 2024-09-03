package com.me.SpringApp.application.abstractions;

import com.me.SpringApp.domain.User.User;

public interface TokenService {
	String generateToken(User user);	
	String validateToken(String token);
}
