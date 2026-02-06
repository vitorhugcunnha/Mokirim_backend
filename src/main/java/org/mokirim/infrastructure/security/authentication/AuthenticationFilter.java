package org.mokirim.infrastructure.security.authentication;

import org.mokirim.infrastructure.security.authentication.token.TokenDecoder;
import org.mokirim.infrastructure.security.context.MokirimPrincipal;
import org.mokirim.infrastructure.security.context.MokirimSecurityContext;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	TokenDecoder tokenDecoder;

	@Override
	public void filter(ContainerRequestContext requestContext) {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			return;
		}

		final var rawToken = authHeader.substring("Bearer ".length());
		final var token = tokenDecoder.decode(rawToken);
		final var mokirimPrincipal = new MokirimPrincipal(token.getUserId(), token.getUsername());
		final var securityContext = new MokirimSecurityContext(mokirimPrincipal);

		requestContext.setSecurityContext(securityContext);
	}
}
