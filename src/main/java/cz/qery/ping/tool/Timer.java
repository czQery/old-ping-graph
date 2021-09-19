package cz.qery.ping.tool;

import cz.qery.ping.main.Graph;
import cz.qery.ping.main.Main;

public class Timer implements Runnable {
    public void run() {
        Delay.time(60);
        String day = "00:00:00";
        String hour = "00:00";
        while(Main.ping) {
            if (hour.equalsIgnoreCase(Time.custom("mm:ss"))) {
                /*
                if (!hour.equalsIgnoreCase(Time.custom("HH:mm"))) {
                    System.out.println("[ALERT] The hour graph has started to generate!");
                    Delay.time(3);
                    Graph.hour();
                }
                 */
            } else if (day.equalsIgnoreCase(Time.basic())) {
                System.out.println("[ALERT] The daily graph has started to generate!");
                Delay.time(3);
                Graph.day();
            }
        }
    }
}
