package de.dseelp.discordsystem.api.event;

public interface Cancelable {
    void setCancelled(boolean cancelled);
    boolean isCancelled();
}
