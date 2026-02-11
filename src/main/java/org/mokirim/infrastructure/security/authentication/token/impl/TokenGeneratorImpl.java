package org.mokirim.infrastructure.security.authentication.token.impl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mokirim.infrastructure.security.authentication.token.TokenGenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenGeneratorImpl implements TokenGenerator {

	@ConfigProperty(name = "token.secret")
	String SECRET;

	@Override
	public String handle() {
		final Key secretToKey = getSecretKey();
		Instant now = Instant.now();
		String token = Jwts.builder()
			.setSubject("550e8400-e29b-41d4-a716-446655440000")
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
			.signWith(secretToKey, SignatureAlgorithm.HS256)
			.compact();

		// TODO: use userId
		return token;
	}

	private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }
}
