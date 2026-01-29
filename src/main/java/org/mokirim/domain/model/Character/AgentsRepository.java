package org.mokirim.domain.model.Character;

import java.util.UUID;

public interface AgentsRepository {

	Agents findAgentById(UUID id);
}
