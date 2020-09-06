package de.dseelp.discordsystem.api.modules;

import lombok.Getter;

import java.io.File;

public abstract class Module {
    public Module() {
        ClassLoader loader = getClass().getClassLoader();
        if (loader instanceof ModuleClassLoader) {
            ((ModuleClassLoader) loader).initialize(this);
        }
    }

    private ModuleInfo info;

    public File getFile() {
        return info.getFile();
    }

    public ModuleInfo getInfo() {
        return info;
    }

    public String getName() {
        return info.getName();
    }

    public String getDescription() {
        return info.getDescription();
    }

    public String[] getAuthors() {
        return info.getAuthors();
    }

    public String getVersion() {
        return info.getVersion();
    }

    public String[] getDependencies() {
        return info.getDependencies();
    }

    public String[] getLoadBefore() {
        return info.getLoadBefore();
    }

    public void onLoad(){}

    public void onEnable(){}

    public void onDisable(){}
    
    final void init(ModuleInfo info) {
        if (this.info == null) {
            this.info = info;
        }else {
            throw new UnsupportedOperationException("Module already initialized!");
        }
    }
    @Getter
    private boolean enabled;

    public void setEnabled(boolean enabled) {
        if (!this.enabled && enabled) {
            enabled = true;
            onEnable();
        }else if (this.enabled && !enabled) {
            onDisable();
            enabled = false;
        }
    }
}
