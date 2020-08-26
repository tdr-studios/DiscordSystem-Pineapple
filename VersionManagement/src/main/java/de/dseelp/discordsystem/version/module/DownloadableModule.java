package de.dseelp.discordsystem.version.module;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class DownloadableModule {
    @Getter
    @Setter
    @Expose
    private DefaultArtifactVersion coreVersion;
    @Getter
    @Setter
    @Expose
    private DefaultArtifactVersion installedVersion;

    @Getter
    private final String name;
    @Getter
    private final String[] authors;
    @Getter
    private final String description;

    @Getter
    @Setter
    private ModuleType type;

    public List<DownloadableModuleVersion> getVersions() {
        Collections.sort(versions);
        return versions;
    }

    private final List<DownloadableModuleVersion> versions = new ArrayList<>();

    public DownloadableModuleVersion getLatest() {
        Collections.sort(versions);
        for (DownloadableModuleVersion version : versions) {
                return version;
        }
        return null;
    }

    public DownloadableModuleVersion getLatestSupported() {
        Collections.sort(versions);
        for (DownloadableModuleVersion version : versions) {
            if (version.isSupported(coreVersion)) {
                return version;
            }
        }
        return null;
    }

    public boolean isUptoDate() {
        if (installedVersion == null) return false;
        int i = getLatestSupported().compareTo(new DownloadableModuleVersion(null, null, installedVersion, 0, null));
        return i >= 0;
    }

    public void addVersion(DownloadableModuleVersion version) {
        versions.add(version);
    }


    public void addVersion(String minCoreVersion, String maxCoreVersion, String version, long created, String downloadUrl) {
        addVersion(new DownloadableModuleVersion(minCoreVersion == null ? null :new DefaultArtifactVersion(minCoreVersion), maxCoreVersion == null ? null : new DefaultArtifactVersion(maxCoreVersion), new DefaultArtifactVersion(version), created, downloadUrl));
    }


}
