package com.weather.dao;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherDAO {

    private static final String API_KEY = "834a1b834a5816c50b24a0134b0261bc";

    public static String getWeather(String city) {
        try {
            String urlStr =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + URLEncoder.encode(city, "UTF-8")
                + "&appid=" + API_KEY
                + "&units=metric";

            HttpURLConnection con =
                (HttpURLConnection) new URL(urlStr).openConnection();

            BufferedReader br =
                new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }

            JSONObject obj = new JSONObject(json.toString());

            double temp = obj.getJSONObject("main").getDouble("temp");
            String desc =
                obj.getJSONArray("weather")
                   .getJSONObject(0)
                   .getString("description");

            return temp + "°C - " + desc;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching weather";
        }
    }
}

