package de.dseelp.discordsystem.launcher;

import lombok.Getter;

public class LauncherMain {

    @Getter
    public boolean GUIActive;
    public static void main(String[] args) {



    }

    public boolean StartGUI() {
        System.out.println("[Launcher] init!");

        if(GUIActive == true) {


            System.err.println("[Launcher] Cant start Laucher! -> Launcher is alredy active!");
            return false;

        }else {
            //Start the Launcher!
            return true;
        }

    }

    public boolean GUIStatus() {

        if(GUIActive) {
            return true;
        }else {
            return false;
        }



    }

    public boolean StopGUI() {

        if(GUIActive) {
            //Stop the GUI
            return true;
        } else {

            System.err.println("Cant Stop the GUI! -> Gui isnt Aktive!");
            return false;
        }

    }
}
