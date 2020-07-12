package de.dseelp.discordsystem.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventManager {
    private List<Listener> listeners;
    public void callEvent(Event event) {
        final Class<?> eventClass = event.getClass();
        for (Listener listener : this.listeners) {
            if (listener instanceof EventListener) {

            }else {
                Class<? extends Listener> aClass = listener.getClass();
                for (Method method : aClass.getMethods()) {
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    if (annotation != null) {
                        if (method.getParameterCount() == 1) {
                            for (Class<?> parameterType : method.getParameterTypes()) {
                                if (parameterType.getName().equals(eventClass.getName())) {
                                    listeners.add(listener);
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
