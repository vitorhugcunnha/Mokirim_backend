package org.mokirim.domain.features.impl;

import java.util.UUID;

import org.mokirim.domain.features.FindCharacterById;
import org.mokirim.domain.model.Character.Character;
import org.mokirim.domain.model.Character.CharacterRepository;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class FindCharacterByIdImpl implements FindCharacterById {

	final private CharacterRepository characterRepository;

	@Override
	public Character handle(UUID id) {

		return characterRepository.findCharacterById(id);
	}
}
