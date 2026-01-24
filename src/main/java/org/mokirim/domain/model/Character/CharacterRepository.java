package org.mokirim.domain.model.Character;

import java.util.UUID;

public interface CharacterRepository {

	Character findCharacterById(UUID id);
}
