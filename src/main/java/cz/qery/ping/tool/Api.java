package cz.qery.ping.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Api {
    //--------------URL
    public static String url(String JsonUrl, String JsonObject, boolean RemoveMarks) {
        try {
            URL url = new URL(JsonUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String src = "";
            while (null != (src = br.readLine())) {
                JsonObject json = new Gson().fromJson(src, JsonObject.class);

                String result = json.get(JsonObject).toString();

                if (RemoveMarks == true) {
                    result = result.replaceAll("\"","");
                    return result;
                } else {
                    return result;
                }
            }
        } catch (Exception ex) {
            String result = null;
            return result;
        }
        return null;
    }
    //--------------TEST
    public static String array(String Json, String JsonObject, boolean RemoveMarks) {
        try {
            JsonObject json = new Gson().fromJson(Json, JsonObject.class);

            JsonArray arr = json.getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
                String post_id = arr.get(i).getAsJsonObject().get(JsonObject).getAsString();
                System.out.println(post_id);
            }
            return null;
        } catch (Exception ex) {
            String result = null;
            return result;
        }
    }
    //--------------JSON
    public static String json(String Json, String JsonObject, boolean RemoveMarks) {
        try {
            JsonObject json = new Gson().fromJson(Json, JsonObject.class);

            String result = json.get(JsonObject).toString();

            if (RemoveMarks == true) {
                result = result.replaceAll("\"","");
                return result;
            } else {
                return result;
            }
        } catch (Exception ex) {
            String result = null;
            return result;
        }
    }
    //--------------CONFIG
    public static String config(String JsonObject, boolean RemoveMarks) {
        try {
            FileReader reader = new FileReader("config.json");
            JsonObject json = new Gson().fromJson(reader, JsonObject.class);
            String result = json.get(JsonObject).toString();

            if (RemoveMarks == true) {
                result = result.replaceAll("\"","");
                return result;
            } else {
                return result;
            }
        } catch (Exception ex) {
            String result = null;
            return result;
        }
    }
}
