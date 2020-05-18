package com.monash.spector.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

/**
 * get weather data from dark-sky api
 */
public class WeatherService {
    final private static String API_KEY =  "6f767f9db62b02dd0ebe2b69013f4212";

    private static WeatherService weatherService = null;

    private WeatherService(){};

    public static WeatherService getInstance(){
        if(weatherService == null){
            weatherService = new WeatherService();
        }
        return weatherService;
    }

    public String getWeatherData(Double longitude, Double latitude){
        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(API_KEY)).language(ForecastRequestBuilder.Language.en)
                .exclude(ForecastRequestBuilder.Block.hourly)
                .location(new GeoCoordinates(new Longitude(longitude), new Latitude(latitude))).build();
        DarkSkyClient client = new DarkSkyClient();
        try {
            String forecast = client.forecastJsonString(request);
            return forecast;
        } catch (ForecastException e) {
            e.printStackTrace();
        }

        return "{\"result\": 0}";
    }


}
