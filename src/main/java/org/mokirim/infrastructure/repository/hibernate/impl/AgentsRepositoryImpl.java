package org.mokirim.infrastructure.repository.hibernate.impl;

import java.util.UUID;

import org.mokirim.domain.model.Character.Agents;
import org.mokirim.domain.model.Character.AgentsRepository;
import org.mokirim.infrastructure.repository.hibernate.entity.EntityUtils;
import org.mokirim.infrastructure.repository.hibernate.panache.AgentsRepositoryPanache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class AgentsRepositoryImpl implements AgentsRepository {

	private final AgentsRepositoryPanache agentsRepositoryPanache;
	private final EntityUtils entityUtils;

	@Override
	public Agents findAgentById(UUID id) {
		var entity = agentsRepositoryPanache.findById(id);

		if (entity == null) {
			throw new NotFoundException("Agent not found.");
		}

		return entityUtils.character(entity);
	}
}
