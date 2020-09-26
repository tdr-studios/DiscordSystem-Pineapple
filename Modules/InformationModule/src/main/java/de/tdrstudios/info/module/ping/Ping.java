package de.tdrstudios.info.module.ping;

import java.io.IOException;

public class Ping {

    public static void main(String[] args) throws IOException, InterruptedException {
        ping("tdrstudios.de");
        if(ping("tdrstudios.de")) {
            System.out.println("[Ping] Host is reacheble!");
        }else {
            System.out.println("[Ping] Host is unreachable!");
        }
    }

    public static boolean ping(String host) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows? "-n" : "-c", "1", host);
        Process proc = processBuilder.start();

        int returnVal = proc.waitFor();
        return returnVal == 0;
    }
}
