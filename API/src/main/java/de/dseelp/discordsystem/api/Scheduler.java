package de.dseelp.discordsystem.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {
    private ExecutorService executorService = Executors.newFixedThreadPool(32);
    private HashMap<Integer, Task> tasks = new HashMap<>();

    public void executeTask(Runnable runnable) {
        Future<?> submit = executorService.submit(runnable);
        int i = getNewInt();
        if (i == -1) return;
        tasks.put(i, new Task(i, submit));
    }

    public void cancelTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            Task task1 = tasks.get(task.getId());
            task1.getFuture().cancel(true);
        }
    }
    private int getNewInt() {
        List<Integer> ids = new ArrayList<>(tasks.keySet());
        Collections.sort(ids);
        for (int i = 1; i < ids.size()+50000; i++) {
            if (!tasks.containsKey(i)){
                return i;
            }
        }
        return -1;
    }
}
