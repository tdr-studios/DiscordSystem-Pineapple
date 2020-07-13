package de.dseelp.discordsystem.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class JsonDocument {

    public JsonDocument(JsonObject object) {
        this.object = object;
    }

    public JsonDocument() {
        this.object = new JsonObject();
    }

    public JsonObject getObject() {
        return object;
    }

    @Override
    public String toString() {
        return GsonUtils.get().toJson(object);
    }

    public String toPrettyString() {
        return GsonUtils.getPretty().toJson(object);
    }

    private JsonObject object;

    public void add(String key, String value) {
        object.addProperty(key, value);
    }

    public void add(String key, Number value) {
        object.addProperty(key, value);
    }

    public void add(String key, Boolean value) {
        object.addProperty(key, value);
    }

    public void add(String key, Character value) {
        object.addProperty(key, value);
    }

    public void addStringList(String key, Collection<String> list) {
        JsonArray array = new JsonArray();
        for (String string : list) {
            array.add(new JsonPrimitive(string));
        }
        object.add(key, array);
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
        object.add(key, array);
    }

    public void addCharacterList(String key, Collection<Character> list) {
        JsonArray array = new JsonArray();
        for (Character character : list) {
            array.add(new JsonPrimitive(character));
        }
        object.add(key, array);
    }

    public String getString(String key) {
        return object.get(key).getAsString();
    }

    public Number getNumber(String key) {
        return object.get(key).getAsNumber();
    }

    public int getInt(String key) {
        return object.get(key).getAsInt();
    }

    public double getDouble(String key) {
        return object.get(key).getAsDouble();
    }

    public long getLong(String key) {
        return object.get(key).getAsLong();
    }

    public float getFloat(String key) {
        return object.get(key).getAsFloat();
    }

    public boolean getBoolean(String key) {
        return object.get(key).getAsBoolean();
    }

    public char getCharacter(String key) {
        return object.get(key).getAsJsonPrimitive().getAsCharacter();
    }

    public Collection<String> getStringList(String key) {
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
        JsonElement element = object.get(key);
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
}
