package cz.qery.ping.main;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;
import org.json.simple.JSONObject;
import cz.qery.ping.event.Read;
import cz.qery.ping.tool.Time;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Graph {
    //-----------------------------------------------------------
    //GENERATE GRAPH HOUR BACK
    //-----------------------------------------------------------
    public static void hour() {
        try {
            Main.ping = false;
            // Prepare the data set
            TimeSeries series = new TimeSeries("ping");
            //LOOP
            boolean loop = true;
            String start = Time.basic();
            String timecode = Time.add_time(Time.basic(), "HOUR", -1);
            while (loop) {
                timecode = Time.add_time(timecode, "SECOND", 1);
                //STOP
                if (timecode.indexOf(start) != -1) {
                    loop = false;
                }
                //System.out.println(timecode);

                //GET DATA
                JSONObject all = (JSONObject) Read.time(Time.date(), timecode);
                JSONObject data = (JSONObject) all.get("Data");
                if (data != null) {
                    long ping = (long) data.get("ping");
                    String time = (String) data.get("time");

                    //TIME CONVERT
                    SimpleDateFormat originalFormat = new SimpleDateFormat("HH:mm:ss");
                    Date runrun = originalFormat.parse(time.toString());
                    //hh
                    SimpleDateFormat hh = new SimpleDateFormat("HH");
                    int run_hh = Integer.parseInt(hh.format(runrun));
                    //mm
                    SimpleDateFormat mm = new SimpleDateFormat("mm");
                    int run_mm = Integer.parseInt(mm.format(runrun));
                    //ss
                    SimpleDateFormat ss = new SimpleDateFormat("ss");
                    int run_ss = Integer.parseInt(ss.format(runrun));

                    //PING CHECKER
                    if (ping < Main.maxping) {
                        //WRITE
                        series.add(new Second(run_ss, run_mm, run_hh, Time.date_dd(), Time.date_mm(), Time.date_yyyy()), ping);

                        //PING CHECKER FIX ABNORMAL PING
                    } else if (ping > Main.maxping && ping < 1000) {
                        //FIX
                        ping = Main.maxping;
                        //WRITE
                        series.add(new Second(run_ss, run_mm, run_hh, Time.date_dd(), Time.date_mm(), Time.date_yyyy()), ping);
                    }
                }
            }
            String filedate = Time.date()+"_"+Time.basic_file();
            String title = Time.date()+"_"+Time.basic();
            //CHART
            TimeSeriesCollection dataset = new TimeSeriesCollection(series);
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Ping graph - "+title,
                    "time",
                    "ms",
                    dataset,
                    true,
                    true,
                    false
            );
            XYPlot plot = chart.getXYPlot();
            NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
            rangeAxis.setRange(-1, Main.maxping);

            ChartUtils.saveChartAsPNG(new File(Main.graph_repository+"hour_"+filedate+".png"), chart, 1920, 1080);
            Main.ping = true;
            System.out.println("[ALERT] Hourly graph created successfully!");
        } catch (IOException e) {
            System.out.println("[ERROR] When generating hourly graph!");
            Main.ping = true;
        } catch (ParseException e) {
            Main.ping = true;
            System.out.println("[ERROR] When generating hourly graph!");
        }
    }
    //-----------------------------------------------------------
    //GENERATE GRAPH DAY BACK
    //-----------------------------------------------------------
    public static void day() {
        try {
            Main.ping = false;
            // Prepare the data set
            TimeSeries series = new TimeSeries("ping");
            //LOOP
            boolean loop = true;
            String start = "00:00:00";
            String timecode = "00:00:00";
            String date = Time.add_date(Time.date(), -1);
            while (loop) {
                timecode = Time.add_time(timecode, "SECOND", 1);
                //STOP
                if (timecode.indexOf(start) != -1) {
                    loop = false;
                }
                //System.out.println(timecode);

                //GET DATA
                JSONObject all = (JSONObject) Read.time(date, timecode);
                JSONObject data = (JSONObject) all.get("Data");
                if (data != null) {
                    long ping = (long) data.get("ping");
                    String time = (String) data.get("time");

                    //TIME CONVERT
                    SimpleDateFormat originalFormat = new SimpleDateFormat("HH:mm:ss");
                    Date runrun = originalFormat.parse(time.toString());
                    //hh
                    SimpleDateFormat hh = new SimpleDateFormat("HH");
                    int run_hh = Integer.parseInt(hh.format(runrun));
                    //mm
                    SimpleDateFormat mm = new SimpleDateFormat("mm");
                    int run_mm = Integer.parseInt(mm.format(runrun));
                    //ss
                    SimpleDateFormat ss = new SimpleDateFormat("ss");
                    int run_ss = Integer.parseInt(ss.format(runrun));

                    //PING CHECKER
                    if (ping < Main.maxping) {
                        //WRITE
                        series.add(new Second(run_ss, run_mm, run_hh, Time.date_dd(), Time.date_mm(), Time.date_yyyy()), ping);

                        //PING CHECKER FIX ABNORMAL PING
                    } else if (ping > Main.maxping && ping < 1000) {
                        //FIX
                        ping = Main.maxping;
                        //WRITE
                        series.add(new Second(run_ss, run_mm, run_hh, Time.date_dd(), Time.date_mm(), Time.date_yyyy()), ping);
                    }
                }
            }
            //CHART
            TimeSeriesCollection dataset = new TimeSeriesCollection(series);
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Ping graph - "+date,
                    "time",
                    "ms",
                    dataset,
                    true,
                    true,
                    false
            );
            XYPlot plot = chart.getXYPlot();
            NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
            rangeAxis.setRange(-1, Main.maxping);

            ChartUtils.saveChartAsPNG(new File(Main.graph_repository+"day_"+date+".png"), chart, 7680, 1080);
            Main.ping = true;
            System.out.println("[ALERT] Daily graph created successfully!");
        } catch (IOException e) {
            System.out.println("[ERROR] When generating daily graph!");
            Main.ping = true;
        } catch (ParseException e) {
            System.out.println("[ERROR] When generating daily graph!");
            Main.ping = true;
        }
    }
}