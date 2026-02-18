package org.mokirim.infrastructure.security.context;

import java.security.Principal;
import java.util.UUID;

import org.mokirim.infrastructure.security.authorization.role.Role;

public class MokirimPrincipal implements Principal {

	private final UUID userId;
	private final String username;
	private final Role role;

	public MokirimPrincipal(UUID userId, String username, Role role) {
		this.userId = userId;
		this.username = username;
		this.role = role;
	}

	@Override
	public String getName() {
		return this.username;
	}

	public UUID getUserId() {
		return this.userId;
	}

	public Role getRole() {
		return this.role;
	}
}
