package org.mokirim.domain.features;

import org.mokirim.domain.model.Character.Character;

import java.util.UUID;

public interface FindCharacterById {

	Character handle(UUID id);
}
