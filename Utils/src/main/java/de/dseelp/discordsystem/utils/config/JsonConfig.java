package de.dseelp.discordsystem.utils.config;

import com.google.gson.*;
import de.dseelp.discordsystem.utils.GsonUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class JsonConfig {
    private JsonObject setted;
    private JsonObject old;
    private JsonConfig defaults;

    private JsonConfig() {

    }

    @SneakyThrows
    public static JsonConfig load(File file) {
        return load(IOUtils.toString(new FileReader(file)));
    }

    public static JsonConfig load(String jsonString) {
        JsonElement element = JsonParser.parseString(jsonString);
        JsonObject obj;
        if (!element.isJsonObject()) {
            obj = new JsonObject();
        }else {
            obj = element.getAsJsonObject();
        }
        JsonConfig config = new JsonConfig();
        config.old = obj;
        config.setted = new JsonObject();
        JsonConfig defaults = new JsonConfig();
        defaults.setted = new JsonObject();
        config.defaults = defaults;
        return config;
    }




    public JsonObject getJsonObject() {
        return setted;
    }

    public JsonObject getDefaults() {
        return setted;
    }

    public void addDefault(String key, String value) {
        if (defaults != null) defaults.add(key, value);
    }

    public void addDefault(String key, Number value) {
        if (defaults != null) defaults.add(key, value);
    }

    public void addDefault(String key, Boolean value) {
        if (defaults != null) defaults.add(key, value);
    }

    public void addDefault(String key, Character value) {
        if (defaults != null) defaults.add(key, value);
    }

    public void addDefaultStringList(String key, Collection<String> list) {
        if (defaults != null) defaults.addDefaultStringList(key, list);
    }

    public void addDefaultUUIDList(String key, Collection<UUID> list) {
        if (defaults != null) defaults.addDefaultUUIDList(key, list);
    }

    public void addDefaultNumberList(String key, Collection<Number> list) {
        if (defaults != null) defaults.addNumberList(key, list);
    }

    public void addDefaultCharacterList(String key, Collection<Character> list) {
        if (defaults != null) defaults.addCharacterList(key, list);
    }


    public void add(String key, String value) {
        setted.addProperty(key, value);
    }

    public void add(String key, Number value) {
        setted.addProperty(key, value);
    }

    public void add(String key, Boolean value) {
        setted.addProperty(key, value);
    }

    public void add(String key, Character value) {
        setted.addProperty(key, value);
    }

    public void addStringList(String key, Collection<String> list) {
        JsonArray array = new JsonArray();
        for (String string : list) {
            array.add(new JsonPrimitive(string));
        }
        setted.add(key, array);
    }

    public void addUUIDList(String key, Collection<UUID> list) {
        List<String> strings = new ArrayList<>();
        for (UUID uuid : list) {
            strings.add(uuid.toString());
        }
        addStringList(key, strings);
    }

    public void addNumberList(String key, Collection<Number> list) {
        JsonArray array = new JsonArray();
        for (Number number : list) {
            array.add(new JsonPrimitive(number));
        }
        setted.add(key, array);
    }

    public void addCharacterList(String key, Collection<Character> list) {
        JsonArray array = new JsonArray();
        for (Character character : list) {
            array.add(new JsonPrimitive(character));
        }
        setted.add(key, array);
    }

    public String getString(String key) {
        return setted.get(key).getAsString();
    }

    public Number getNumber(String key) {
        return setted.get(key).getAsNumber();
    }

    public int getInt(String key) {
        return setted.get(key).getAsInt();
    }

    public double getDouble(String key) {
        return setted.get(key).getAsDouble();
    }

    public long getLong(String key) {
        return setted.get(key).getAsLong();
    }

    public float getFloat(String key) {
        return setted.get(key).getAsFloat();
    }

    public boolean getBoolean(String key) {
        return setted.get(key).getAsBoolean();
    }

    public char getCharacter(String key) {
        return setted.get(key).getAsJsonPrimitive().getAsCharacter();
    }

    public Collection<String> getStringList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<String> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsString());
                    return list;
                }
            }
        }
        return null;
    }

    public Collection<UUID> getUUIDList(String key) {
        Collection<String> strings = getStringList(key);
        List<UUID> uuids = new ArrayList<>();
        for (String string : strings) {
            uuids.add(UUID.fromString(string));
        }
        return uuids;
    }

    public Collection<Number> getNumberList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Number> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsNumber());
                    return list;
                }
            }
        }
        return null;
    }

    public Collection<Integer> getIntList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Integer> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsInt());
                    return list;
                }
            }
        }
        return null;
    }

    public Collection<Long> getLongList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Long> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsLong());
                    return list;
                }
            }
        }
        return null;
    }

    public List<Double> getDoubleList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Double> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsDouble());
                    return list;
                }
            }
        }
        return null;
    }

    public List<Float> getFloatList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Float> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsFloat());
                    return list;
                }
            }
        }
        return null;
    }

    public Collection<Character> getCharList(String key) {
        JsonElement element = setted.get(key);
        if (element.isJsonArray()) {
            List<Character> list = new ArrayList<>();
            JsonArray array = element.getAsJsonArray();
            for (JsonElement jsonElement : array) {
                if (jsonElement.isJsonPrimitive()) {
                    list.add(jsonElement.getAsJsonPrimitive().getAsCharacter());
                    return list;
                }
            }
        }
        return null;
    }

    @SneakyThrows
    public void save(File file) {
        JsonObject obj = defaults.getJsonObject().deepCopy();
        for (Map.Entry<String, JsonElement> elementEntry : old.entrySet()) {
            obj.add(elementEntry.getKey(), elementEntry.getValue());
        }
        for (Map.Entry<String, JsonElement> elementEntry : setted.entrySet()) {
            obj.add(elementEntry.getKey(), elementEntry.getValue());
        }
        String s = GsonUtils.getPretty().toJson(obj);
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
