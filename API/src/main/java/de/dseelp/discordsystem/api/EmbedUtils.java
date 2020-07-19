package de.dseelp.discordsystem.api;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;

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



    public static EmbedBuilder createSuccessBuilder(String title, String description) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.GREEN);
        builder.setTitle(title);
        builder.setDescription(description);
        return builder;
    }

    public static MessageEmbed createSuccess(String title, String description) {
        return createSuccessBuilder(title, description).build();
    }

    public static EmbedBuilder createNormalBuilder(String title, String description) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle(title);
        builder.setDescription(description);
        return builder;
    }

    public static MessageEmbed createNormal(String title, String description) {
        return createNormalBuilder(title, description).build();
    }

    public static EmbedBuilder createWarningBuilder(String title, String description) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);
        builder.setTitle(title);
        builder.setDescription(description);
        return builder;
    }

    public static MessageEmbed createWarning(String title, String description) {
        return createWarningBuilder(title, description).build();
    }

    public static EmbedBuilder addUserFooter(EmbedBuilder builder, User user) {
        return builder.setFooter(user.getName(), user.getAvatarUrl());
    }

    public static EmbedBuilder setTimestamp(EmbedBuilder builder, TemporalAccessor accessor) {
        builder.setTimestamp(accessor);
        return builder;
    }

    public static EmbedBuilder setTimestamp(EmbedBuilder builder, long time) {
        return setTimestamp(builder, Instant.ofEpochMilli(time));
    }
}
