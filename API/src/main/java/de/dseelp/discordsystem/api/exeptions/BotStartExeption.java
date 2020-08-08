package de.dseelp.discordsystem.api.exeptions;

import lombok.Getter;

public class BotStartExeption extends Exception{

    @Getter
    public static String errormsg = "The Bot cant be Start!";
    public BotStartExeption() {


        super(errormsg);

    }


}
