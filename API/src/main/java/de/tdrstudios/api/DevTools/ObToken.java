package de.tdrstudios.api.DevTools;

import de.dseelp.discordsystem.api.BotConfig;
import lombok.Getter;

public class ObToken {

    @Getter
    public static String token = BotConfig.getToken();
}
