package de.dseelp.discordsystem.utils;

public enum Emojis {
    GRINNING("\uD83D\uDE01");


    private final String smiley;

    Emojis(String smiley) {
        this.smiley = smiley;
    }

    public String getSmiley() {
        return smiley;
    }
}
