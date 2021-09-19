package cz.qery.ping.event;

import cz.qery.ping.main.Graph;
import cz.qery.ping.main.Main;
import cz.qery.ping.tool.Delay;

import java.util.Scanner;

public class Input implements Runnable {
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            String input = in.nextLine();
            if ("stop".equalsIgnoreCase(input)) {
                Main.ping = false;
                Delay.time(3);
                System.out.println("[ALERT] Ping terminated!");
                System.exit(0);
            } else if ("graph hour".equalsIgnoreCase(input)) {
                System.out.println("[ALERT] The hour graph has started to generate!");
                Delay.time(3);
                Graph.hour();
            } else if ("graph day".equalsIgnoreCase(input)) {
                System.out.println("[ALERT] The daily graph has started to generate!");
                Delay.time(3);
                Graph.day();
            } else {
                input = "";
            }
        }
    }
}
