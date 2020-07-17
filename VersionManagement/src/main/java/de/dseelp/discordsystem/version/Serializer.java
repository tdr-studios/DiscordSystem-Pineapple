package de.dseelp.discordsystem.version;

import com.google.gson.*;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.lang.reflect.Type;

public class Serializer implements JsonSerializer<DefaultArtifactVersion>, JsonDeserializer<DefaultArtifactVersion> {

    @Override
    public DefaultArtifactVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return new DefaultArtifactVersion(json.getAsString());
    }

    @Override
    public JsonElement serialize(DefaultArtifactVersion src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
