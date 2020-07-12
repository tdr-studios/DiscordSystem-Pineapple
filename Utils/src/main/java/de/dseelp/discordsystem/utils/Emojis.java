package de.dseelp.discordsystem.utils;

public enum Emojis {
    GRINNING("\uD83D\uDE01"),
	JOY("😂"),
	SMILEY("😃"),
	SMILE("😄"),
	GRIN("work-in-progress"),
	SWEAT_SMILE("😅"),
	LAUGING("😆"),
	INTELLIJ("🚽"),
	WINK("😉"),
	BLUSH("😊"),
	YUM("😋"),
	PENSIVE("😌"),
	HEART_EYEY("😍"),
	SEYY("😏"),
	UNAMUSED("😒"),
	SWEAT("😓"),
	DISSAPOINTET("😔"),
	CONFOUNDED("😖"),
	KISSING_HEART("😘"),
	RELAXED("😚"),
	ZANY_FACE("😜"),
	STUCK_OUT_TOUNGE_CLOSED_EYES("😝"),
	PENSIVE2("😞"), // two diffrent Names on Global and Discord Use
	WORRIED("😠"),
	RAGE("😡"),
	CRY("😢"),
	TRIUMPF("😤"),
	DISAPPOINTET_RELIEVED("😥"),
	FEARFUL("😨");
	
	
	


    private final String smiley;

    Emojis(String smiley) {
        this.smiley = smiley;
    }

    public String getSmiley() {
        return smiley;
    }
}

