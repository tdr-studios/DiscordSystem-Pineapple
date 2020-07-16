package de.dseelp.discordsystem.utils.version.core;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class DownloadableCore {
    @Getter
    @Expose
    private final DefaultArtifactVersion currentVersion;
    @Getter
    private List<DownloadableCoreVersion> versions = new ArrayList<>();

    public DownloadableCoreVersion getLatest() {
        Collections.sort(versions);
        for (DownloadableCoreVersion version : versions) {
                return version;
        }
        return null;
    }

    public boolean isUptoDate() {
        DownloadableCoreVersion version = new DownloadableCoreVersion(currentVersion, 0, "");
        int i = getLatest().compareTo(version);
        System.out.println(i);
        return i >= 0;
    }

    public void addVersion(DownloadableCoreVersion version) {
        versions.add(version);
    }


    public void addVersion(String minCoreVersion, String maxCoreVersion, String version, long created, String downloadUrl) {
        addVersion(new DownloadableCoreVersion(new DefaultArtifactVersion(version), created, downloadUrl));
    }


}
