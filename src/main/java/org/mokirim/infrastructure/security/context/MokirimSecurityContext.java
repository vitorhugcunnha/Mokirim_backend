package org.mokirim.infrastructure.security.context;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;

public class MokirimSecurityContext implements SecurityContext {

	private final Principal principal;

	public MokirimSecurityContext(Principal principal) {
        this.principal = principal;
    }

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		return false; // TODO: Implements roles in Mokirim (main character, NPC, enemies, etc.)
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }
}
