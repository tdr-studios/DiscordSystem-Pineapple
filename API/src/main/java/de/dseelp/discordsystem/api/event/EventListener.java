package de.dseelp.discordsystem.api.event;

@Deprecated
public interface EventListener<T> extends Listener {
   void onEvent(T event);
}
