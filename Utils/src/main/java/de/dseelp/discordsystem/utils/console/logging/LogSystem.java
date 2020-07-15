package de.dseelp.discordsystem.utils.console.logging;

import de.dseelp.discordsystem.utils.console.ActionOutputStream;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import lombok.Getter;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class LogSystem {
    @Getter
    private SystemLogger logger;

    private PrintStream system = System.out;

    public LogSystem(SystemLogger logger) {
        this.logger = logger;
        initWriter();
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
    private PrintStream debug;

    @Getter
    private PrintStream out;

    @Getter
    private PrintStream warning;

    @Getter
    private PrintStream error;

    private PrintStream create(Level level) {
        return new PrintStream(new ActionOutputStream(new Consumer<String>() {
            @Override
            public void accept(String s) {
                logger.log(level.getAssigned(), s);
            }
        }), true);
    }
}
