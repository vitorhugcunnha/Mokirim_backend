package org.mokirim.infrastructure.repository.hibernate.impl;

import java.util.UUID;

import org.mokirim.domain.model.Character.Character;
import org.mokirim.domain.model.Character.CharacterRepository;
import org.mokirim.infrastructure.repository.hibernate.entity.EntityUtils;
import org.mokirim.infrastructure.repository.hibernate.panache.CharacterRepositoryPanache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class CharacterRepositoryImpl implements CharacterRepository {

	private final CharacterRepositoryPanache characterRepositoryPanache;
	private final EntityUtils entityUtils;

	@Override
	public Character findCharacterById(UUID id) {
		var entity = characterRepositoryPanache.findById(id);

		if (entity == null) {
			throw new NotFoundException("Character not found.");
		}

		return entityUtils.character(entity);
	}
}
