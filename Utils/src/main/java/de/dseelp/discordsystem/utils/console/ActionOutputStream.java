package de.dseelp.netcloud.lib.console;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Consumer;

public class ActionOutputStream extends ByteArrayOutputStream {
    private final Consumer<String> consumer;

    public ActionOutputStream(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    private final String separator = System.lineSeparator();

    @Override
    public void flush() throws IOException {
        synchronized (this) {
            super.flush();
            String record = this.toString();
            super.reset();
            if ((record.length() > 0) && (!record.equals(separator))) {
                consumer.accept(record);
            }

        }
    }
}
