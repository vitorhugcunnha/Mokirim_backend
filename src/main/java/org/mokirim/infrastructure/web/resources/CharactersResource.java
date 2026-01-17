package org.mokirim.infrastructure.web.resources;

import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/characters")
public class CharactersResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCharacterById(
		@PathParam("id") Integer id) {

	}
}
