package de.dseelp.discordsystem.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    private static final Gson gson = new GsonBuilder().create();
    private static final Gson pretty = new GsonBuilder().setPrettyPrinting().create();

    public static Gson get() {
        return gson;
    }

    public static Gson getPretty() {
        return pretty;
    }
}
