package me.aborozdykh.agileenginetest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class AuthUtil {
    private static final String URL = "http://interview.agileengine.com/auth";

    public String getBearerToken(String apiKey) {
        try {
            URL url = new URL(URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            connection.setDoOutput(true);
            String jsonString = "{ \"apiKey\": \"" + apiKey + "\" }";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Object obj = new JSONParser().parse(response.toString());
                JSONObject jo = (JSONObject) obj;
                Boolean auth = (Boolean) jo.get("auth");
                String token = (String) jo.get("token");
                return token;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "Wrong";
    }
}
