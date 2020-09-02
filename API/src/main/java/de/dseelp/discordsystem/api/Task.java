package de.dseelp.discordsystem.api;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Future;

@Getter
public class Task {
    private final int id;
    private final Future<?> future;

    public Task(int id, Future<?> future) {
        this.id = id;
        this.future = future;
    }

    public void cancel() {
        Discord.getScheduler().cancelTask(this);
    }
}
