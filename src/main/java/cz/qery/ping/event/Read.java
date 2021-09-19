package cz.qery.ping.event;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
@SuppressWarnings("unchecked")
public class Read {
    public static JSONObject date(String date) throws IOException {
        JSONObject result = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("Data/"+date+".json");
            JSONObject savedData = (JSONObject) parser.parse(reader);
            //JSONArray data = new JSONArray();
            JSONArray all = (JSONArray) savedData.get(date);

            result.put("Success", true);
            result.put("Data", all);
            reader.close();
            return result;
        } catch (Exception e) {
            result.put("Success", false);
            result.put("Message", "Data not found!");
            return result;
        }
    }
    public static JSONObject time(String date, String time) throws IOException {
        JSONObject result = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("Data/"+date+".json");
            JSONObject savedData = (JSONObject) parser.parse(reader);
            //JSONArray data = new JSONArray();
            JSONArray all = (JSONArray) savedData.get(date);

            for (int i = 0; i < all.size(); ++i) {
                JSONObject item = (JSONObject) all.get(i);
                String ttime = (String) item.get("time");
                if (time.indexOf(ttime) != -1) {
                    result.put("Success", true);
                    result.put("Data", item);
                    reader.close();
                    return result;
                }
            }

            result.put("Success", false);
            result.put("Message", "Data not found!");
            return result;
        } catch (Exception e) {
            result.put("Success", false);
            result.put("Message", "Data not found!");
            return result;
        }
    }
}
