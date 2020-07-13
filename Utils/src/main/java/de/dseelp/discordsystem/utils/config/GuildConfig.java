package de.dseelp.discordsystem.utils.config;

import de.dseelp.discordsystem.utils.JsonDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuildConfig {
    private long id;
    private JsonDocument document;
}
