package com.me.SpringApp.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.me.SpringApp.application.abstractions.TokenService;
import com.me.SpringApp.domain.User.User;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${api.security.token.secret_key}")
	private String secret;

	@Value("${api.security.token.issuer}")
	private String issuer;

	@Override
	public String generateToken(User user) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer(issuer).withSubject(user.getLogin())
					.withExpiresAt(generateTokenExpirationTime()).sign(algorithm);

			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Failed to generate token");
		}
	}

	@Override
	public String validateToken(String token) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);
			DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
			return decodedJWT.getSubject();
		} catch (JWTVerificationException exception) {
			return "";
		}
	}

	public Instant generateTokenExpirationTime() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
