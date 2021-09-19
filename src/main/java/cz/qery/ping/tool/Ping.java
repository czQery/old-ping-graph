package cz.qery.ping.tool;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class Ping {
    public static boolean reachable(String address, int time) {
        try {
            InetAddress geek = InetAddress.getByName(address);
            return geek.isReachable(time);
        } catch (IOException e) {
            return false;
        }
    }
    public static long time(String address, int time) {
        try {
            long timeToRespond = 0;
            Date start = new Date();
            InetAddress geek = InetAddress.getByName(address);
            if (geek.isReachable(time)) {
                Date stop = new Date();
                timeToRespond = (stop.getTime() - start.getTime());
            } else {
                timeToRespond = -1;
            }
            return timeToRespond;
        } catch (IOException e) {
            return -1;
        }
    }
}
