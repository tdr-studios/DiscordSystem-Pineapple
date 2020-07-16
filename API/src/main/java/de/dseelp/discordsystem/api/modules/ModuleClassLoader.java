package de.dseelp.discordsystem.api.modules;

import lombok.Getter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {
    @Getter
    private final ModuleInfo info;
    @Getter
    private Module module;
    @Getter
    private boolean initialized;
    public ModuleClassLoader(File file, ModuleInfo info) {
        super(urlToArray(fileToUrl(file)));
        this.info = info;
    }

    public void initialize(Module module) {
        if (!initialized) {
            initialized = true;
            this.module = module;
            module.init(info);
        }
    }


    private static URL fileToUrl(File file) {
        try {
            return new URL("jar:" + file.toURI().toURL() + "!/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URL[] urlToArray(URL url) {
        return new URL[]{url};
    }
}
