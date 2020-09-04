package de.dseelp.discordsystem.core.spring.jpa.repositories;

import de.dseelp.discordsystem.core.spring.jpa.entities.RawGuildConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<RawGuildConfig, Long> {
}
