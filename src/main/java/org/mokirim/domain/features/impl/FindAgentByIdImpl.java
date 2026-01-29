package org.mokirim.domain.features.impl;

import java.util.UUID;

import org.mokirim.domain.features.FindAgentById;
import org.mokirim.domain.model.Character.Agents;
import org.mokirim.domain.model.Character.AgentsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class FindAgentByIdImpl implements FindAgentById {

	final private AgentsRepository characterRepository;

	@Override
	public Agents handle(UUID id) {

		return characterRepository.findAgentById(id);
	}
}
