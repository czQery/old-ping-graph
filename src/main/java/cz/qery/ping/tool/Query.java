package cz.qery.ping.tool;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.Splitter;

public class Query {
    /*
    public static String get(String QueryString, String Object) {
        try {
            Map<String,String> queryParameters = Splitter
                    .on("?")
                    .withKeyValueSeparator("=")
                    .split(QueryString);
            return queryParameters.get(Object);
        } catch (Exception e) {
            return null;
        }
    }
     */
    public static Map<String, String> get(String query) throws UnsupportedEncodingException {
        try {
            Map<String, String> query_pairs = new LinkedHashMap<String, String>();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return query_pairs;
        } catch (Exception e) {
            return null;
        }
    }
}
