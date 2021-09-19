package cz.qery.ping.main;

import java.io.*;
import java.net.InetSocketAddress;

import cz.qery.ping.tool.Api;
import cz.qery.ping.tool.Query;
import cz.qery.ping.tool.Timer;
import org.json.simple.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import cz.qery.ping.event.Input;
import cz.qery.ping.event.Read;

@SuppressWarnings("unchecked")
public class Main {
    //VERSION
    public static double v = 1.0;

    //CONST
    public static boolean ping = true;
    public static String api_name = Api.config("api_name", true);
    public static int port = Integer.parseInt(Api.config("port", false));
    public static String token = Api.config("token", true);
    public static String address = Api.config("address", true);
    public static int maxping = Integer.parseInt(Api.config("maxping", false));
    public static String graph_repository = Api.config("graph_repository", true);


    //MAIN
    public static void main(String[] args) throws Exception {
        System.out.println("[INFO] This tool requires root permissions!\n");
        System.out.println("[INFO] Ping v"+v+" - created by czQery");
        //Multithreading
        Input input = new Input();
        Timer timer = new Timer();
        Thread t1 = new Thread(input, "t1");
        Thread t2 = new Thread(timer, "t2");
        //DATA FILE CHECK
        File dataFile = new File("Data");
        if (!dataFile.isFile()) {
            dataFile.mkdir();
        }
        //START Input
        t1.start();

        //START Timer
        t2.start();

        //START Api
        Api_start();

        //START Ping
        Checker.event();
    }


    //API SERVER
    public static void Api_start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Api_default());
        //server.createContext("/" + api_name + "/total", new Api_total());
        server.createContext("/" + api_name + "/time", new Api_time());
        server.createContext("/" + api_name + "/date", new Api_date());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("[INFO] ApiServer Started");
    }
    static class Api_default implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String query = t.getRequestURI().getQuery();
            String token = Query.get(query).get("token");
            if (token != null && token.indexOf(Main.token) != -1) {
                JSONObject output = new JSONObject();
                JSONObject result = new JSONObject();
                output.put("Logged", true);
                result.put("Success", false);
                result.put("Message", "No result found!");
                output.put("Result", result);
                String response = output.toJSONString();
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            } else {
                JSONObject output = new JSONObject();
                JSONObject result = new JSONObject();
                output.put("Logged", false);
                result.put("Success", false);
                result.put("Message", "Access denied!");
                output.put("Result", result);
                String response = output.toJSONString();
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }
        }
    }




    //API-TOTAL
    /*
    static class Api_total implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String query = t.getRequestURI().getQuery();
            String token = Query.get(query, "token");
            if (token != null && token.indexOf(Main.token) != -1) {
                String response = "{\"Logged\":true,\"Result\":\"Api_total\"}";
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            } else {
                String response = "{\"Logged\":false,\"Result\":\"Access denied!\"}";
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }
        }
    }*/




    //API-LAST
    static class Api_time implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            JSONObject output = new JSONObject();
            JSONObject result = new JSONObject();
            String query = t.getRequestURI().getQuery();
            String token = Query.get(query).get("token");
            String date = Query.get(query).get("date");
            String time = Query.get(query).get("time");
            //LOGGED TRUE
            if (token != null && token.indexOf(Main.token) != -1) {
                //>>FINAL!<<
                if (date != null && time != null) {
                    output.put("Logged", true);
                    output.put("Result", Read.time(date, time));
                    //
                    String response = output.toJSONString();
                    t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes("UTF-8"));
                    os.close();
                    //DATE AND TIME NULL
                } else {
                    output.put("Logged", true);
                    result.put("Success", false);
                    result.put("Message", "Date and time cannot be empty!");
                    output.put("Result", Read.time(date, time));
                    //
                    String response = output.toJSONString();
                    t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes("UTF-8"));
                    os.close();
                }
                //LOGGED FALSE
            } else {
                output.put("Logged", false);
                result.put("Success", false);
                result.put("Message", "Access denied!");
                output.put("Result", result);
                //
                String response = output.toJSONString();
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }
        }
    }




    //API-SPECIFIC
    static class Api_date implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            JSONObject output = new JSONObject();
            JSONObject result = new JSONObject();
            String query = t.getRequestURI().getQuery();
            String token = Query.get(query).get("token");
            String date = Query.get(query).get("date");
            //LOGGED TRUE
            if (token != null && token.indexOf(Main.token) != -1) {
                //>>FINAL!<<
                if (date != null) {
                    output.put("Logged", true);
                    output.put("Result", Read.date(date));
                    //
                    String response = output.toJSONString();
                    t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes("UTF-8"));
                    os.close();
                    //DATE NULL
                } else {
                    output.put("Logged", true);
                    result.put("Success", false);
                    result.put("Message", "Date cannot be empty!");
                    output.put("Result", Read.date(date));
                    //
                    String response = output.toJSONString();
                    t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes("UTF-8"));
                    os.close();
                }
                //LOGGED FALSE
            } else {
                output.put("Logged", false);
                result.put("Success", false);
                result.put("Message", "Access denied!");
                output.put("Result", result);
                //
                String response = output.toJSONString();
                t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes("UTF-8"));
                os.close();
            }
        }
    }

}
