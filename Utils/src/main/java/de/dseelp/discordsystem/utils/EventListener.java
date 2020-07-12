package de.dseelp.discordsystem.utils;

public interface EventListener<T> extends Listener{
   void onEvent(T event);
}
