package de.dseelp.discordsystem.core.impl;

import de.dseelp.discordsystem.core.spring.jpa.entities.RawGuildConfig;
import de.dseelp.discordsystem.core.spring.jpa.repositories.GuildRepository;
import de.dseelp.discordsystem.api.GuildManager;
import de.dseelp.discordsystem.utils.GsonUtils;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.utils.config.GuildConfig;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultGuildManager implements GuildManager {

    @Autowired
    private GuildRepository repository;

    @Override
    public GuildConfig getGuildConfig(Guild guild) {
        return getGuildConfig(guild.getIdLong());
    }

    @Override
    public GuildConfig getGuildConfig(long id) {
        Optional<RawGuildConfig> optional = repository.findById(id);
        if (optional.isPresent()) {
            RawGuildConfig guildConfig = optional.get();
            return new GuildConfig(id, new JsonDocument(GsonUtils.get().toJsonTree(guildConfig.getJson()).getAsJsonObject()));
        }else {
            return null;
        }
    }

    @Override
    public void save(GuildConfig config) {
        repository.save(new RawGuildConfig(config.getId(), config.getDocument().toString()));
    }
}
