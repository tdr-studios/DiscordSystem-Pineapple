package de.dseelp.discordsystem.utils.version.module;

import com.google.gson.annotations.Expose;
import de.dseelp.discordsystem.utils.version.module.DownloadableModuleVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class DownloadableModule {
    @Getter
    @Expose
    private final DefaultArtifactVersion coreVersion;
    @Getter
    @Expose
    private final DefaultArtifactVersion installedVersion;
    @Getter
    private List<DownloadableModuleVersion> versions = new ArrayList<>();

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
        addVersion(new DownloadableModuleVersion(new DefaultArtifactVersion(minCoreVersion), new DefaultArtifactVersion(maxCoreVersion), new DefaultArtifactVersion(version), created, downloadUrl));
    }


}
