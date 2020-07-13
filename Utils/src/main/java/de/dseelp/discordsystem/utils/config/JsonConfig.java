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

<<<<<<< HEAD
    public void setDefault(String key, String value) {
        if (defaults != null) defaults.set(key, value);
    }

    public void setDefault(String key, Number value) {
        if (defaults != null) defaults.set(key, value);
    }

    public void setDefault(String key, Boolean value) {
        if (defaults != null) defaults.set(key, value);
    }

    public void setDefault(String key, Character value) {
        if (defaults != null) defaults.set(key, value);
    }

    public void setDefaultStringList(String key, Collection<String> list) {
        if (defaults != null) defaults.setDefaultStringList(key, list);
    }

    public void setDefaultUUIDList(String key, Collection<UUID> list) {
        if (defaults != null) defaults.setUUIDList(key, list);
    }

    public void setDefaultNumberList(String key, Collection<Number> list) {
        if (defaults != null) defaults.setNumberList(key, list);
    }

    public void setDefaultCharacterList(String key, Collection<Character> list) {
        if (defaults != null) defaults.setCharacterList(key, list);
    }


    public void set(String key, String value) {
        setted.addProperty(key, value);
    }

    public void set(String key, Number value) {
        setted.addProperty(key, value);
    }

    public void set(String key, Boolean value) {
        setted.addProperty(key, value);
    }

    public void set(String key, Character value) {
        setted.addProperty(key, value);
    }

    public void setStringList(String key, Collection<String> list) {
=======
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
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
        JsonArray array = new JsonArray();
        for (String string : list) {
            array.add(new JsonPrimitive(string));
        }
        setted.add(key, array);
    }

<<<<<<< HEAD
    public void setUUIDList(String key, Collection<UUID> list) {
=======
    public void addUUIDList(String key, Collection<UUID> list) {
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
        List<String> strings = new ArrayList<>();
        for (UUID uuid : list) {
            strings.add(uuid.toString());
        }
<<<<<<< HEAD
        setStringList(key, strings);
    }

    public void setNumberList(String key, Collection<Number> list) {
=======
        addStringList(key, strings);
    }

    public void addNumberList(String key, Collection<Number> list) {
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
        JsonArray array = new JsonArray();
        for (Number number : list) {
            array.add(new JsonPrimitive(number));
        }
        setted.add(key, array);
    }

<<<<<<< HEAD
    public void setCharacterList(String key, Collection<Character> list) {
=======
    public void addCharacterList(String key, Collection<Character> list) {
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
        JsonArray array = new JsonArray();
        for (Character character : list) {
            array.add(new JsonPrimitive(character));
        }
        setted.add(key, array);
    }

    public String getString(String key) {
<<<<<<< HEAD
        return toObject().get(key).getAsString();
    }

    public Number getNumber(String key) {
        return toObject().get(key).getAsNumber();
    }

    public int getInt(String key) {
        return toObject().get(key).getAsInt();
    }

    public double getDouble(String key) {
        return toObject().get(key).getAsDouble();
    }

    public long getLong(String key) {
        return toObject().get(key).getAsLong();
    }

    public float getFloat(String key) {
        return toObject().get(key).getAsFloat();
    }

    public boolean getBoolean(String key) {
        return toObject().get(key).getAsBoolean();
    }

    public char getCharacter(String key) {
        return toObject().get(key).getAsJsonPrimitive().getAsCharacter();
    }

    public Collection<String> getStringList(String key) {
        JsonElement element = toObject().get(key);
=======
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
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        JsonElement element = toObject().get(key);
=======
        JsonElement element = setted.get(key);
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
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
<<<<<<< HEAD
        String s = GsonUtils.getPretty().toJson(toObject());
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
    }

    private JsonObject toObject() {
=======
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
        JsonObject obj = defaults.getJsonObject().deepCopy();
        for (Map.Entry<String, JsonElement> elementEntry : old.entrySet()) {
            obj.add(elementEntry.getKey(), elementEntry.getValue());
        }
        for (Map.Entry<String, JsonElement> elementEntry : setted.entrySet()) {
            obj.add(elementEntry.getKey(), elementEntry.getValue());
        }
<<<<<<< HEAD
        return obj;
=======
        String s = GsonUtils.getPretty().toJson(obj);
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
>>>>>>> 087e19de3b1fc4383614c32a9e537ee2fad95b83
    }
}
