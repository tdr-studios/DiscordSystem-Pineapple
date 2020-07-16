package de.dseelp.discordsystem.api.modules;

import java.io.File;
import java.io.FilenameFilter;

public class FileExtensionFilter implements FilenameFilter {

    private final String extension;

    public FileExtensionFilter(String extension) {
        this.extension = extension.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
