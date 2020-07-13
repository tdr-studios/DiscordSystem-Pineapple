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
<<<<<<< HEAD

    public static EmbedBuilder createNormalBuilder(String title, String description) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);
        builder.setTitle(title);
        builder.setDescription(description);
        return builder;
    }

    public static MessageEmbed createNormal(String title, String description) {
        return createErrorBuilder(title, description).build();
    }
=======
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
}
