package cz.qery.ping.tool;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Time {
    public static String basic() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return f.format(now);
    }
    public static String date() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        return f.format(now);
    }
    public static int date_dd() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        return Integer.parseInt(f.format(now));
    }
    public static int date_mm() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        return Integer.parseInt(f.format(now));
    }
    public static int date_yyyy() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        return Integer.parseInt(f.format(now));
    }
    public static String basic_file() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        return f.format(now);
    }
    public static String add_time(String Original, String Type, int Amount) throws ParseException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date d = df.parse(Original);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            if (Type == "SECOND") {
                cal.add(Calendar.SECOND, Amount);
                return df.format(cal.getTime());
            } else if (Type == "MINUTE") {
                cal.add(Calendar.MINUTE, Amount);
                return df.format(cal.getTime());
            } else if (Type == "HOUR") {
                cal.add(Calendar.HOUR, Amount);
                return df.format(cal.getTime());
            } else {
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String add_date(String Original, int Amount) throws ParseException {
        try {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Date d = df.parse(Original);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, Amount);
            return df.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String custom(String format) {
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern(format);
            LocalDateTime now = LocalDateTime.now();
            return f.format(now);
        } catch (Exception e) {
            return null;
        }
    }
}
