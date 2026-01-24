package org.mokirim.domain.model.Character;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class CharacterBuilder {

	public Character build(UUID id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
		return new Character(id, name, description, createdAt, updatedAt);
	}
}
