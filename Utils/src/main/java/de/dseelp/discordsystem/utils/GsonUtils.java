package de.dseelp.discordsystem.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class GsonUtils {
    private static HashMap<Type, Object> adapters = new HashMap<>();

    public static void addAdapter(Type type, Object object) {
        adapters.put(type, object);
        GsonBuilder gsonBuilder = new GsonBuilder();
        GsonBuilder prettyBuilder = new GsonBuilder().setPrettyPrinting();
        adapters.forEach(new BiConsumer<Type, Object>() {
            @Override
            public void accept(Type type, Object object) {
                prettyBuilder.registerTypeAdapter(type, object);
                gsonBuilder.registerTypeAdapter(type, object);
            }
        });
        try {
            FieldUtils.writeDeclaredStaticField(GsonUtils.class, "gson", gsonBuilder.create(), true);
            FieldUtils.writeDeclaredStaticField(GsonUtils.class, "pretty", prettyBuilder.create(), true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Gson gson = new GsonBuilder().create();
    private static Gson pretty = new GsonBuilder().setPrettyPrinting().create();

    public static Gson get() {
        return gson;
    }

    public static Gson getPretty() {
        return pretty;
    }
}
