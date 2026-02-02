package org.mokirim.infrastructure.security.authentication.token;

public interface TokenDecoder {
	DecodedToken decode(String token);
}
