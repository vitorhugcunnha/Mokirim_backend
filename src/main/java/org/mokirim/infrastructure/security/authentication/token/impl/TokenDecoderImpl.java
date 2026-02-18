package org.mokirim.infrastructure.security.authentication.token.impl;

import java.util.UUID;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mokirim.infrastructure.security.authentication.token.DecodedToken;
import org.mokirim.infrastructure.security.authentication.token.TokenDecoder;
import org.mokirim.infrastructure.security.authorization.role.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenDecoderImpl implements TokenDecoder {

	@ConfigProperty(name = "token.secret")
	String SECRET;

	@Override
	public DecodedToken decode(String token) {

		if (SECRET == null || SECRET.isBlank()) {
			throw new IllegalStateException("TOKEN_SECRET not configured");
		}

		try {
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();

			UUID userId = UUID.fromString(claims.getSubject());
			String username = claims.get("username", String.class);
			Role role = claims.get("role", Role.class);

			return new DecodedToken(userId, username, role);
		} catch (JwtException error) {
			System.err.println("Falha ao decodificar token: " + error.getMessage());
			throw new UnauthorizedException();
		}
	}
}
