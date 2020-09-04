package de.dseelp.discordsystem.utils.console;

import de.dseelp.discordsystem.utils.console.logging.LoggerRegistry;
import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        this(true);
    }

    public Console(boolean debug) {
        if (debug) {
            System.setProperty("org.jline.terminal.dumb", "true");
        }
        AnsiConsole.systemInstall();
        try {
            terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = (LineReaderImpl) LineReaderBuilder.builder().terminal(terminal).build();
        /*System.setOut(new PrintStream(new ActionOutputStream(new Consumer<String>() {
            @Override
            public void accept(String s) {
                write(s);
            }
        }), true));
         */

    }

    public void init() {
        threadStarted = true;
        createThread();
        //thread.start();
    }

    private ExecutorService service = Executors.newFixedThreadPool(32);

    private void createThread() {
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (threadStarted) {
                    try {
                        String line = reader.readLine(prompt);
                        readHandlers.forEach(new BiConsumer<String, Consumer<String>>() {
                            @Override
                            public void accept(String s, Consumer<String> consumer) {
                                service.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            consumer.accept(line);
                                        }catch (Exception exception) {
                                            exception.printStackTrace(LoggerRegistry.get().getError());
                                        }
                                    }
                                });
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
        //thread.interrupt();
        service.shutdownNow();
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
