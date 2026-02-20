package org.mokirim.infrastructure.security.authorization;

import org.mokirim.infrastructure.security.authorization.annotation.Secured;
import org.mokirim.infrastructure.security.authorization.role.Role;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Inject
	ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) {

		final var secured = getSecuredAnnotation();

		if (secured == null || Boolean.TRUE.equals(secured.optional())) {
			return;
		}

		final var securityContext = requestContext.getSecurityContext();

		if (securityContext != null) {
			if (securityContext.getUserPrincipal() == null) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
				return;
			}

			for (Role role : secured.value()) {
				if (securityContext.isUserInRole(role.name())) {
					return;
				}
			}
		}
	}

	private Secured getSecuredAnnotation() {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(Secured.class)) {
            return resourceInfo.getResourceMethod().getAnnotation(Secured.class);
        }
        return resourceInfo.getResourceClass().getAnnotation(Secured.class);
    }
}
