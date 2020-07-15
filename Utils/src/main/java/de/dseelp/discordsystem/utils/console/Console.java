package de.dseelp.netcloud.lib.console;

import com.google.common.collect.Maps;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.jline.keymap.KeyMap;
import org.jline.reader.*;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.NonBlockingReaderImpl;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Console {
    private Terminal terminal;
    private LineReaderImpl reader;
    private String prompt = "prompt>";
    private HashMap<String, Consumer<String>> readHandlers = new HashMap<>();
    private Thread thread;
    private boolean threadStarted;

    public Console() {
        this(false);
    }

    public Console(boolean debug) {
        if (debug) {
            System.setProperty("org.jline.terminal.dumb", "true");
        }
        AnsiConsole.systemInstall();
        try {
            terminal = TerminalBuilder.builder().system(true).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = (LineReaderImpl) LineReaderBuilder.builder().terminal(terminal).build();
        System.setOut(new PrintStream(new ActionOutputStream(new Consumer<String>() {
            @Override
            public void accept(String s) {
                write(s);
            }
        }), true));
    }

    public void init() {
        threadStarted = true;
        createThread();
        thread.start();
    }

    private void createThread() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadStarted) {
                    try {
                        String line = reader.readLine(prompt);
                        readHandlers.forEach(new BiConsumer<String, Consumer<String>>() {
                            @Override
                            public void accept(String s, Consumer<String> consumer) {
                                consumer.accept(line);
                            }
                        });
                    }catch (UserInterruptException ex) {

                    }
                }
            }
        });
    }

    public void shutdown() {
        threadStarted = false;
        thread.interrupt();
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void write(String s) {
        reader.printAbove(s);
    }

    public void addReadHandler(String name, Consumer<String> readHandler) {
        if (!readHandlers.containsKey(name)) {
            readHandlers.put(name, readHandler);
        }
    }

    public void removeReadHandler(String name) {
        readHandlers.remove(name);
    }
}
