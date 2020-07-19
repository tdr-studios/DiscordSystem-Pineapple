package de.dseelp.discordsystem.utils.console;

import de.dseelp.discordsystem.utils.console.logging.SystemLogger;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;

public class ConsoleSystem {
    private static boolean init = false;
    @Getter
    private static Console console;
    private static java.util.logging.Logger rootLogger;

    @SneakyThrows
    public static void init(Console console) {
        if (!init) {
            init = true;
            ConsoleSystem.console = console;
            rootLogger = java.util.logging.Logger.getAnonymousLogger();
            rootLogger.setUseParentHandlers(false);
            rootLogger.setLevel(Level.FINE);
            CustomConsoleHandler consoleHandler = new CustomConsoleHandler();
            consoleHandler.setFormatter(new RootFormatter(true));
            File file = new File("logs");
            if (!file.exists()) {
                file.mkdir();
            }
            rootLogger.addHandler(consoleHandler);
            Log4JAdapterHandler log4JAdapterHandler = new Log4JAdapterHandler();
            log4JAdapterHandler.setFormatter(new RootFormatter(false));
            rootLogger.addHandler(log4JAdapterHandler);
            PatternLayout layout = new PatternLayout("%m%n");
            DailyRollingFileAppender fileAppender =
                    new DailyRollingFileAppender( layout, "logs/latest.log", "'.'yyyy-MM-dd_HH-mm" );
            logger.setLevel(org.apache.log4j.Level.DEBUG);
            logger.addAppender(fileAppender);
        }
    }

    private static Logger logger = Logger.getRootLogger();

    private static class Log4JAdapterHandler extends ConsoleHandler {
        @Override
        public void publish(LogRecord record) {
            if (!isLoggable(record)) {
                return;
            }
            if (record.getLevel() == Level.CONFIG) {
                logger.debug(getFormatter().format(record));
            }else if (record.getLevel() == Level.INFO) {
                logger.info(getFormatter().format(record));
            }else if (record.getLevel() == Level.WARNING) {
                logger.warn(getFormatter().format(record));
            }else if (record.getLevel() == Level.SEVERE) {
                logger.error(getFormatter().format(record));
            }
        }
    }

    private static class CustomConsoleHandler extends ConsoleHandler {
        @Override
        public void publish(LogRecord record) {
            if (!isLoggable(record)) {
                return;
            }
            console.write(getFormatter().format(record));
        }
    }

    private static class RootFormatter extends Formatter {

        private final boolean colored;

        private final boolean lined;

        private RootFormatter(boolean colored) {
            this.colored = colored;
            this.lined = false;
        }

        public RootFormatter(boolean colored, boolean lined) {
            this.colored = colored;
            this.lined = lined;
        }

        @Override
        public String format(LogRecord record) {
            String s;
            if (record.getLoggerName() == null || record.getLoggerName().equals("")) {
                s = getString(de.dseelp.discordsystem.utils.console.logging.Level.get(record.getLevel()), colored)+record.getMessage();
            }else {
                s = getString(de.dseelp.discordsystem.utils.console.logging.Level.get(record.getLevel()), colored)+record.getLoggerName()+" "+record.getMessage();
            }
            if (lined) s=s+System.lineSeparator();
            return s;
        }
    }



    public static class LogHandler extends ConsoleHandler {
        @Getter
        private SystemLogger logger;

        public LogHandler(SystemLogger logger) {
            this.logger = logger;
        }

        @Override
        public void publish(LogRecord record) {
            boolean logg = isLoggable(record);
            if (!logg) {
                return;
            }
            if (record.getParameters() == null) record.setParameters(new SystemLogger[]{});
            record.setParameters(addStringToArray((SystemLogger[]) record.getParameters(), logger));
            if (logger.getParent().equals(rootLogger)) {
                ConsoleSystem.publish(this, record);
            }else {
                logger.getParent().log(record);
            }
        }
    }

    public static SystemLogger[] addStringToArray(SystemLogger[] array, SystemLogger s) {
        List<SystemLogger> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));
        list.add(s);
        return list.toArray(new SystemLogger[list.size()]);
    }

    private static void publish(LogHandler handler, LogRecord record) {
        StringBuilder builder = new StringBuilder();
        List<SystemLogger> params = Arrays.asList((SystemLogger[]) record.getParameters());
        Collections.reverse(params);
        if (params.size() != 1) {
            boolean first = true;
            for (SystemLogger param : params) {
                if (param.isNoTree()) continue;
                if (!first) builder.append(braceColor+"/"+ConsoleColor.DEFAULT);
                if (first) first = false;
                builder.append(param.getDisplayName());
            }
            LogRecord test = new LogRecord(record.getLevel(), record.getMessage());
            test.setLoggerName("["+builder.toString()+"]:");
            sendTest(test);
            //sendAndSplit(getColoredString(de.dseelp.discordsystem.utils.console.logging.Level.get(record.getLevel()))+" ["+builder.toString()+"]: ", record.getMessage());
        }else {
            LogRecord test = new LogRecord(record.getLevel(), record.getMessage());
            sendTest(record);
            //sendAndSplit(getColoredString(de.dseelp.discordsystem.utils.console.logging.Level.get(record.getLevel())), " "+record.getMessage());
        }
    }

    private static void sendTest(LogRecord record) {
        String lineSep = System.lineSeparator();
        if (record.getMessage().contains(lineSep)) {
            for (String s1 : record.getMessage().split(System.lineSeparator())) {
                LogRecord line = new LogRecord(record.getLevel(), s1);
                line.setLoggerName(record.getLoggerName());
                rootLogger.log(line);
            }
        }else
            rootLogger.log(record);
    }

    private static void sendAndSplit(String prefix, String s) {
        String lineSep = System.lineSeparator();
        if (s.contains(lineSep)) {
            for (String s1 : s.split(System.lineSeparator())) {
                console.write(prefix+s1);
            }
        }else
            console.write(prefix+s);
    }

    public static SystemLogger createLogger(String name) {
        SystemLogger logger = new SystemLogger(name);
        logger.setLevel(Level.CONFIG);
        logger.disableTree();
        logger.fixNoTree();
        logger.setParent(rootLogger);
        return logger;
    }

    public static SystemLogger createSubLogger(SystemLogger logger, String name) {
        SystemLogger sub = new SystemLogger(name);
        sub.setLevel(Level.CONFIG);
        sub.setParent(logger);
        return sub;
    }

    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM HH:mm:ss");

    private static final ConsoleColor braceColor = ConsoleColor.DARK_GRAY;

    private static final ConsoleColor defaultColor = ConsoleColor.DEFAULT;

    private static String getString(de.dseelp.discordsystem.utils.console.logging.Level level, boolean colored) {
        StringBuilder builder = new StringBuilder();
        if (colored) builder.append(braceColor);
        builder.append("[");
        if (colored) builder.append(ConsoleColor.WHITE);
        builder.append(format.format(java.lang.System.currentTimeMillis()));
        if (colored) builder.append(braceColor);
        builder.append("]");
        if (colored) builder.append(defaultColor);
        builder.append(" ");
        builder.append(level);
        if (colored) builder.append(braceColor);
        builder.append(": ");
        if (colored) builder.append(ConsoleColor.DEFAULT);
        return builder.toString();
    }
}
