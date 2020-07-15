package de.dseelp.netcloud.lib.console;

import de.dseelp.netcloud.lib.console.logging.SystemLogger;
import lombok.Getter;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ConsoleSystem {
    private static boolean init = false;
    @Getter
    private static Console console;
    private static Logger rootLogger;

    public static void init(Console console) {
        if (!init) {
            init = true;
            ConsoleSystem.console = console;
            rootLogger = Logger.getAnonymousLogger();
            rootLogger.setUseParentHandlers(false);
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
            if (!isLoggable(record)) {
                return;
            }
            if (record.getParameters() == null) record.setParameters(new SystemLogger[]{});
            record.setParameters(addStringToArray((SystemLogger[]) record.getParameters(), logger));
            logger.getParent().log(record);
            if (logger.getParent().equals(rootLogger)) {
                ConsoleSystem.publish(this, record);
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
                if (!first) builder.append("/");
                if (first) first = false;
                builder.append(param.getDisplayName());
            }
            System.out.println("["+builder.toString()+"]:"+record.getMessage());
        }else {
            System.out.println(record.getMessage());
        }
    }

    public static SystemLogger createLogger(String name) {
        SystemLogger logger = new SystemLogger(name);
        logger.disableTree();
        logger.fixNoTree();
        logger.setParent(rootLogger);
        return logger;
    }

    public static SystemLogger createSubLogger(SystemLogger logger, String name) {
        SystemLogger sub = new SystemLogger(name);
        sub.setParent(logger);
        return sub;
    }
}
