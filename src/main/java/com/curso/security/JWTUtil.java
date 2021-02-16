package com.curso.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${spring.jwt.secret}")
	private String secret;

	@Value("${spring.jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {

		Date now = new Date();
		Date validity = new Date(System.currentTimeMillis() + expiration);

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
}
