package org.mokirim.infrastructure.security.context;

import java.security.Principal;
import java.util.UUID;

public class MokirimPrincipal implements Principal {

	private final UUID userId;
	private final String username;

	public MokirimPrincipal(UUID userId, String username) {
		this.userId = userId;
		this.username = username;
	}

	@Override
	public String getName() {
		return this.username;
	}

	public UUID getUserId() {
		return this.userId;
	}
}
