package de.dseelp.discordsystem.utils;

public enum Emojis {
    GRINNING("\uD83D\uDE01"),
	JOY("😂"),
	SMILEY("😃"),
	SMILE("😄"),
	GRIN("work-in-progress"),
	SWEAT_SMILE("😅"),
	LAUGING("😆"),
	INTELLIJ("🚽");
	
	


    private final String smiley;

    Emojis(String smiley) {
        this.smiley = smiley;
    }

    public String getSmiley() {
        return smiley;
    }
}

