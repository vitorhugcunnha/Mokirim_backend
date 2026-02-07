package org.mokirim.infrastructure.security.authentication.token.impl;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mokirim.infrastructure.security.authentication.token.TokenGenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGeneratorImpl implements TokenGenerator {

	@ConfigProperty(name = "token.secret")
	Key SECRET;

	@Override
	public String handle() {
		Instant now = Instant.now();
		String token = Jwts.builder()
			.setSubject("550e8400-e29b-41d4-a716-446655440000") // TODO: use userId
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plus(1L, ChronoUnit.WEEKS)))
			.signWith(SECRET, SignatureAlgorithm.HS256)
			.compact();

		return token;
	}
}
