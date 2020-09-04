package de.dseelp.discordsystem.version.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.dseelp.discordsystem.utils.GsonUtils;
import de.dseelp.discordsystem.utils.JsonDocument;
import de.dseelp.discordsystem.version.WebHelper;
import de.dseelp.discordsystem.version.module.DownloadableModule;
import de.dseelp.discordsystem.version.module.ModuleType;
import lombok.Getter;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

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
