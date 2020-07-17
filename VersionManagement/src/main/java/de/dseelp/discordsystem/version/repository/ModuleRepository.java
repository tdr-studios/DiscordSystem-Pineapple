package de.dseelp.discordsystem.version.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.dseelp.discordsystem.utils.GsonUtils;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.version.WebHelper;
import de.dseelp.discordsystem.version.core.DownloadableCoreVersion;
import de.dseelp.discordsystem.version.module.DownloadableModule;
import de.dseelp.discordsystem.version.module.ModuleType;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import sun.security.pkcs11.Secmod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModuleRepository {
    @Getter
    private List<DownloadableModule> modules;

    @Getter
    private String name;

    @Getter
    private ModuleRepositoryType type;

    private ModuleRepository() {}

    public static ModuleRepository findRepository(WebHelper webHelper) {
        JsonDocument config = webHelper.getFromFile("config.repo");
        boolean useDefault = config == null;
        String fileName = "repository.json";
        if (!useDefault) {
            if (config.has("moduleRepo")) {
                JsonDocument doc = config.getDocument("moduleRepo");
                if (doc.has("fileName")) {
                    fileName = doc.getString("fileName");
                }
            }
        }
        JsonDocument document = webHelper.getFromFile(fileName);
        if (document == null) throw new IllegalArgumentException("Repo not valid! "+webHelper.getBaseUrl());
        String repoName = document.getString("name");
        ModuleRepositoryType type = ModuleRepositoryType.OPTIONAL;
        if (document.has("type")) {
            type = ModuleRepositoryType.valueOf(document.getString("type").toUpperCase());
        }
        JsonArray array = document.getObject().getAsJsonArray("modules");
        List<DownloadableModule> modules = new ArrayList<>();
        for (JsonElement element : array) {
            if (element.isJsonObject()) {
                DownloadableModule module = GsonUtils.get().fromJson(element, DownloadableModule.class);
                if (module.getType() == null) module.setType(ModuleType.UNDEFINED);
                modules.add(module);
            }
        }
        ModuleRepository repository = new ModuleRepository();
        repository.modules = modules;
        repository.name = repoName;
        repository.type = type;
        return repository;
    }

    public ModuleRepository setCoreVersion(String version) {
        DefaultArtifactVersion coreVersion = new DefaultArtifactVersion(version);
        for (DownloadableModule module : modules) {
            module.setCoreVersion(coreVersion);
        }
        return this;
    }
}
