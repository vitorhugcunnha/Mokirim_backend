package org.mokirim.infrastructure.repository.hibernate.entity;

import org.mokirim.domain.model.Character.Agents;
import org.mokirim.domain.model.Character.AgentsBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class EntityUtils {

	private final AgentsBuilder characterBuilder;

	public Agents character(AgentsEntity agentsEntity) {
		return characterBuilder.build(
									agentsEntity.getId(),
									agentsEntity.getName(),
									agentsEntity.getDescription(),
									agentsEntity.getCreatedAt(),
									agentsEntity.getUpdatedAt());
	}
}
