package org.mokirim.infrastructure.security.authentication;

import org.mokirim.infrastructure.security.authentication.token.TokenDecoder;
import org.mokirim.infrastructure.security.authorization.annotation.Secured;
import org.mokirim.infrastructure.security.context.MokirimPrincipal;
import org.mokirim.infrastructure.security.context.MokirimSecurityContext;

import io.jsonwebtoken.JwtException;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	TokenDecoder tokenDecoder;

	@Inject
	ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) {

		Secured secured = getSecuredAnnotation();

		if (secured == null) {
			return;
		}

		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || !authHeader.startsWith("Mokirim ")) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			return;
		}

		try {
			final var rawToken = authHeader.substring("Mokirim ".length());
			final var token = tokenDecoder.decode(rawToken);
			final var mokirimPrincipal = new MokirimPrincipal(token.getUserId(), token.getUsername());
			final var securityContext = new MokirimSecurityContext(mokirimPrincipal);
			requestContext.setSecurityContext(securityContext);
		} catch (JwtException error) {
			throw new UnauthorizedException("Invalid token " + error);
		}

	}

	private Secured getSecuredAnnotation() {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(Secured.class)) {
            return resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        }
        return resourceInfo.getResourceClass().getAnnotation(Secured.class);
    }
}
