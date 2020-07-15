package de.dseelp.discordsystem.utils.console.logging;

import lombok.Getter;

public enum Level {
    ERROR(java.util.logging.Level.SEVERE),
    WARNING(java.util.logging.Level.WARNING),
    INFO(java.util.logging.Level.INFO),
    DEBUG(java.util.logging.Level.CONFIG);

    @Getter
    private java.util.logging.Level assigned;

    Level(java.util.logging.Level assigned) {

        this.assigned = assigned;
    }
}
