package de.dseelp.netcloud.lib.console.logging;

import de.dseelp.netcloud.lib.console.ConsoleSystem;
import lombok.Getter;

import java.util.logging.Logger;

public class SystemLogger extends Logger {
    @Getter
    private final String displayName;
    @Getter
    private boolean noTree;
    @Getter
    private boolean fixed = false;

    public void disableTree() {
        if (!fixed) noTree = true;
    }

    public void enableTree() {
        if (!fixed) noTree = false;
    }

    public void fixNoTree() {
        fixed = true;
    }

    public SystemLogger(String displayName) {
        super("", null);
        enableTree();
        this.displayName = displayName;
        setUseParentHandlers(false);
        addHandler(new ConsoleSystem.LogHandler(this));
    }

    @Override
    public String toString() {
        return displayName;
    }
}
