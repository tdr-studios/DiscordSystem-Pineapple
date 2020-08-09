package de.dseelp.discordsystem.utils.console.logging;

import de.dseelp.discordsystem.utils.console.ActionOutputStream;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import lombok.Getter;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LogSystem {
    @Getter
    private SystemLogger logger;

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


    public void lineSeperator() {
        this.write(" ");

    }
    public void lineSeperator(int count) throws InterruptedException {
        while(count > 0) {
            this.lineSeperator();
            count = count - 1;
            TimeUnit.MILLISECONDS.sleep(2);
        }

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
