package de.dseelp.netcloud.lib.console.logging;

import de.dseelp.netcloud.lib.console.ActionOutputStream;
import lombok.Getter;

import java.io.PrintWriter;
import java.util.function.Consumer;

public class LogSystem {
    private SystemLogger logger;

    public LogSystem(SystemLogger logger) {
        this.logger = logger;
    }

    private void initWriter() {
        error = create(Level.ERROR);
        warning = create(Level.WARNING);
        out = create(Level.INFO);
        debug = create(Level.DEBUG);
    }

    public void error(String msg) {
        logger.severe(msg);
    }

    public void warning(String msg) {
        logger.warning(msg);
    }

    public void write(String msg) {
        logger.info(msg);
    }

    public void debug(String msg) {
        logger.config(msg);
    }

    @Getter
    private PrintWriter debug;

    @Getter
    private PrintWriter out;

    @Getter
    private PrintWriter warning;

    @Getter
    private PrintWriter error;

    private PrintWriter create(Level level) {
        return new PrintWriter(new ActionOutputStream(new Consumer<String>() {
            @Override
            public void accept(String s) {
                logger.log(level.getAssigned(), s);
            }
        }), true);
    }
}
