package de.tdrstudios.utils;



public enum SenderType{
    CONSOLE("Console-Command-Sender"),
    Guild("Guild-Command-Sender"),
    Privat("Pricat-Command-Sender");










    private final String sendertype;

    SenderType(String sendertype) {
        this.sendertype = sendertype;
    }

    public String getSendertype() {
        return sendertype;
    }
}
