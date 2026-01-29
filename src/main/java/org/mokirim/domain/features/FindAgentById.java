package org.mokirim.domain.features;

import org.mokirim.domain.model.Character.Agents;

import java.util.UUID;

public interface FindAgentById {

	Agents handle(UUID id);
}
