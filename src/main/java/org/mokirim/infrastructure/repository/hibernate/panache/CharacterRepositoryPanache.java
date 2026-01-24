package org.mokirim.infrastructure.repository.hibernate.panache;

import java.util.UUID;

import org.mokirim.infrastructure.repository.hibernate.entity.CharacterEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class CharacterRepositoryPanache
	implements PanacheRepositoryBase<CharacterEntity, UUID> {
}
