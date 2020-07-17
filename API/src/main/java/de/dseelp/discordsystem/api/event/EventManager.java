package de.dseelp.discordsystem.api.event;

import de.dseelp.discordsystem.api.DiscordModule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private HashMap<DiscordModule, List<Listener>> listeners = new HashMap<>();

    private Listener commandListener;
    public void setCommandListener(Listener listener) {
        if (commandListener == null) {
            commandListener = listener;
        }
    }

    public void callEvent(Event event) {
        if (commandListener != null) call(commandListener, event);
        for (Map.Entry<DiscordModule, List<Listener>> entry : listeners.entrySet()) {
            for (Listener listener : entry.getValue()) {
                call(listener, event);
            }
        }
    }

    private void call(Listener listener, Event event) {
        if (listener instanceof EventListener) {

        }else {
            Class<? extends Listener> aClass = listener.getClass();
            for (Method method : aClass.getMethods()) {
                EventHandler annotation = method.getAnnotation(EventHandler.class);
                if (annotation != null) {
                    if (method.getParameterCount() == 1) {
                        for (Class<?> parameterType : method.getParameterTypes()) {
                            if (parameterType.getName().equals(event.getClass().getName())) {
                                method.setAccessible(true);
                                try {
                                    method.invoke(listener, event);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void addListener(DiscordModule module, Listener listener) {
        listeners.computeIfAbsent(module, k -> new ArrayList<>());
        List<Listener> listeners = this.listeners.get(module);
        listeners.add(listener);
        this.listeners.put(module, listeners);
    }

    public void removeListener(DiscordModule module) {
        listeners.remove(module);
    }
}
