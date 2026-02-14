package org.mokirim.infrastructure.web.resources;

import org.mokirim.infrastructure.security.authentication.token.TokenGenerator;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/user")
@AllArgsConstructor
public class UserResource {

	private final TokenGenerator tokenGenerator;

	@GET
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateToken() {

		final String token = tokenGenerator.handle();

		return Response.ok(token)
				.status(Response.Status.CREATED)
				.build();
	}
}
