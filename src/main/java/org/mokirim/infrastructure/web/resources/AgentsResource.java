package org.mokirim.infrastructure.web.resources;

import java.util.UUID;

import org.mokirim.domain.features.FindAgentById;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/characters")
@AllArgsConstructor
public class AgentsResource {

	private final FindAgentById findCharacterById;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacterById(
					@PathParam("id") UUID id) {

			final var character = findCharacterById.handle(id);
			return Response.ok(character)
					.status(Response.Status.OK)
					.build();
	}
}
