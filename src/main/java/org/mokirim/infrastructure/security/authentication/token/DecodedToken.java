package org.mokirim.infrastructure.security.authentication.token;

import java.util.UUID;

import org.mokirim.infrastructure.security.authorization.role.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DecodedToken {
	private final UUID userId;
	private final String username;
	private final Role role;
}
