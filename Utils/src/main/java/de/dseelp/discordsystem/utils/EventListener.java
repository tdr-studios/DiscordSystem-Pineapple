package de.dseelp.discordsystem.utils;

@Deprecated
public interface EventListener<T> extends Listener{
   void onEvent(T event);
}
