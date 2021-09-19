package cz.qery.ping.tool;

import java.util.concurrent.TimeUnit;

public class Delay {
    public static void time(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
