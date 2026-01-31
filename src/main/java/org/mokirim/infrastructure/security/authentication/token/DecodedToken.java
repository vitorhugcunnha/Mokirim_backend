package org.mokirim.infrastructure.security.authentication.token;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DecodedToken {
	private final UUID userId;
	private final String username;
}
