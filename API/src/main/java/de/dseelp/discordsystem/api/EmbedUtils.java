package de.dseelp.discordsystem.api;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class EmbedUtils {
    public static EmbedBuilder createErrorBuilder(String title, String description) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED);
        builder.setTitle(title);
        builder.setDescription(description);
        return builder;
    }

    public static MessageEmbed createError(String title, String description) {
        return createErrorBuilder(title, description).build();
    }
}
