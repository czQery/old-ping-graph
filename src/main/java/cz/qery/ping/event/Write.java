package cz.qery.ping.event;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import cz.qery.ping.tool.Time;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("unchecked")
public class Write {
    public static void data(long ping) throws IOException {
        long end_time = System.currentTimeMillis();

        String time = Time.basic();
        String date = Time.date();

        try {
            File dataFile = new File("Data/"+date+".json");
            if (!dataFile.isFile()) {
                JSONObject savedData = new JSONObject();
                JSONArray all = new JSONArray();
                JSONObject data = new JSONObject();

                data.put("time", time);
                data.put("ping", ping);

                all.add(data);
                savedData.put(date, all);

                Writer writer = new FileWriter("Data/"+date+".json");
                writer.write(savedData.toJSONString());
                writer.close();
            } else {
                Reader reader = new FileReader("Data/"+date+".json");
                JSONParser parser = new JSONParser();
                JSONObject savedData = (JSONObject) parser.parse(reader);
                JSONArray all = (JSONArray) savedData.get(date);
                JSONObject data = new JSONObject();

                data.put("time", time);
                data.put("ping", ping);

                all.add(data);
                savedData.put(date, all);

                Writer writer = new FileWriter("Data/"+date+".json");
                writer.write(savedData.toJSONString());
                writer.close();
                reader.close();
            }
        } catch (Exception e) {
            System.out.println("[ERROR] When writing data");
        }
    }
}
