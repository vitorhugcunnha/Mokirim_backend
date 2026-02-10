package org.mokirim.infrastructure.web.resources;

import java.util.UUID;

import org.mokirim.domain.features.FindAgentById;
import org.mokirim.infrastructure.security.authorization.annotation.Secured;
import org.mokirim.infrastructure.security.authorization.role.Role;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;

@Path("/characters")
@AllArgsConstructor
public class AgentsResource {

	private final FindAgentById findCharacterById;

	@GET
	@Path("/{id}")
	@Secured({ Role.PLAYER })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacterById(
					@PathParam("id") UUID id,
					@Context SecurityContext securityContext) {

			final var character = findCharacterById.handle(id);
			return Response.ok(character)
					.status(Response.Status.OK)
					.build();
	}
}
