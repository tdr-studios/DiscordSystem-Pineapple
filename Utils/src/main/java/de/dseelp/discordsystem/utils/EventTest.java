package de.dseelp.discordsystem.utils;

import lombok.Getter;

public class EventTest {
    public static void main(String[] args) {

    }

    private static class TestEvent extends Event {
        @Getter
        private final String test;

        private TestEvent(String test) {
            this.test = test;
        }
    }

    private static class ListenerTest implements Listener {
        @EventHandler
        public void onTest(TestEvent event) {
            System.out.println(event.getTest());
        }
    }
}
