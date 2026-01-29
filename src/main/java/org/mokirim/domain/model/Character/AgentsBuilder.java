package org.mokirim.domain.model.Character;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class AgentsBuilder {

	public Agents build(UUID id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Agents(id, name, description, createdAt, updatedAt);
	}
}
