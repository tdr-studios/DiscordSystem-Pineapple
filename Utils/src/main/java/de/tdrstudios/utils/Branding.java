package de.tdrstudios.utils;



public enum Branding {
    Normal("tdrstudios"),
    Big1("【T】【D】【R】【S】【T】【U】【D】【I】【O】【S】"),
    Rocket("░▒▓█►─═  ᵗ\uD835\uDCD3ʳｓ\uD835\uDCC9ù\uD835\uDD55Ⓘ\uD835\uDC28ｓ ═─◄█▓▒░\n"),
    COOLBRAND("🆃🅳🆁🆂🆃🆄🅳🅸🅾🆂");









    private final String branding;

    Branding(String branding) {
        this.branding = branding;
    }

    public String getBranding() {
        return branding;
    }
}
