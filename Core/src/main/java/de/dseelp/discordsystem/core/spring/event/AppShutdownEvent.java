package de.dseelp.discordsystem.core.spring.event;

import org.springframework.context.ApplicationEvent;

public class AppShutdownEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AppShutdownEvent(Object source) {
        super(source);
    }
}
