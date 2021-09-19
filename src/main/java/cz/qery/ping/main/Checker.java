package cz.qery.ping.main;

import java.io.IOException;

import cz.qery.ping.event.Write;
import cz.qery.ping.tool.Delay;
import cz.qery.ping.tool.Ping;

public class Checker {
    public static void event() throws IOException {
        boolean loop = true;
        while (loop) {
            while (Main.ping) {
                long ping = Ping.time(Main.address, Main.maxping);
                if (ping != -1) {
                    Write.data(ping);
                } else {
                    Write.data(ping);
                    //System.out.println("[ALERT] Down: "+ Main.address);
                }
                Delay.time(1);
            }
            Delay.time(1);
        }
    }
}
