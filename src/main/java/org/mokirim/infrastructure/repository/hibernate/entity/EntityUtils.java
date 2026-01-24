package org.mokirim.infrastructure.repository.hibernate.entity;

import org.mokirim.domain.model.Character.Character;
import org.mokirim.domain.model.Character.CharacterBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class EntityUtils {

	private final CharacterBuilder characterBuilder;

	public Character character(CharacterEntity characterEntity) {
		return characterBuilder.build(
									characterEntity.getId(),
									characterEntity.getName(),
									characterEntity.getDescription(),
									characterEntity.getCreatedAt(),
									characterEntity.getUpdatedAt());
	}
}
