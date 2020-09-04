package de.dseelp.discordsystem.core.spring.event;

import org.springframework.context.ApplicationEvent;

public class AppFinishedStartedEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AppFinishedStartedEvent(Object source) {
        super(source);
    }
}
