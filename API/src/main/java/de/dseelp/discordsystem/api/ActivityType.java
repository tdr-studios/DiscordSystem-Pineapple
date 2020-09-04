package de.dseelp.discordsystem.api;

import net.dv8tion.jda.api.entities.Activity;

public enum ActivityType {
    CUSTOM,
    PLAYING,
    LISTENING,
    STREAMING,
    WATCHING;

    public Activity.ActivityType toDiscordActivityType() {
        if (this == CUSTOM) return Activity.ActivityType.CUSTOM_STATUS;
        else if (this == PLAYING) return Activity.ActivityType.DEFAULT;
        else if (this == LISTENING) return Activity.ActivityType.LISTENING;
        else if (this == STREAMING) return Activity.ActivityType.STREAMING;
        else if (this == WATCHING) return Activity.ActivityType.WATCHING;
        return null;
    }
}
