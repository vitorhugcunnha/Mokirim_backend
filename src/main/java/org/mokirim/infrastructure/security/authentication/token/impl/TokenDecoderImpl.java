package org.mokirim.infrastructure.security.authentication.token.impl;

import java.util.UUID;

import org.mokirim.infrastructure.security.authentication.token.DecodedToken;
import org.mokirim.infrastructure.security.authentication.token.TokenDecoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.quarkus.security.UnauthorizedException;

public class TokenDecoderImpl implements TokenDecoder {

	@Override
	public DecodedToken decode(String token) {

		final String SECRET = System.getenv("TOKEN_SECRET");

		try {
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();

			UUID userId = UUID.fromString(claims.getSubject());
			String username = claims.get("username", String.class);

			return new DecodedToken(userId, username);
		} catch (JwtException error) {
			throw new UnauthorizedException("Invalid token");
		}
	}
}
