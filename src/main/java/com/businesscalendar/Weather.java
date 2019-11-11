package com.businesscalendar;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Weather implements UseApi {

    private String whatCity;

    private String name;

    private String temperature;

    private String humidity;

    private String pressure;

    private String windSpeed;

    private String description;

    public void setWhatCity(String whatCity) {
        this.whatCity = whatCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatCity() {
        return whatCity;
    }

    public void setCity(String string) {
        if (string.contains(" ")){
            whatCity=string.replaceAll(" ","%20");
        } else {
            whatCity=string;
        }
    }

    @Override
    public String buildUrl() {
        final String Api_Url = "http://api.openweathermap.org/data/2.5/weather?q=";

        final String Api_Key = "&appid=90bcdbe722d5a9d22b619a3ec856395e&lang=en";

        String requestURL = new StringBuilder(Api_Url).append(getWhatCity()).append(Api_Key).toString();

        return requestURL;
    }

    @Override
    public String getResponse(String url) throws IOException {
        String response = "";

        response = new ApiConnectionService().connectionWithAPI(url);

        return response;
    }

    @Override
    public List parseJson(String json) {

        Double temp;
        int humid=0;
        int press=0;
        Double wind;

        JSONObject rootObject = new JSONObject(json);
        if (rootObject.getInt("cod") == 200) {
            JSONObject mainObject = rootObject.getJSONObject("main");
            DecimalFormat df = new DecimalFormat("#.##");
            temp = mainObject.getDouble("temp");
            temp = temp - 273;

            setTemperature(temp.toString() + " \u00b0C");

            name=rootObject.getString("name");

            humid = mainObject.getInt("humidity");
            setHumidity(humid + " %");

            press = mainObject.getInt("pressure");
            setPressure(press + " hPa");

            JSONObject windObject = rootObject.getJSONObject("wind");
            wind = windObject.getDouble("speed");
            setWindSpeed(wind.toString()+ " m/s");
            JSONArray weather = rootObject.getJSONArray("weather");
            JSONObject weatherObject = weather.getJSONObject(0);
            description = weatherObject.getString("description");
        }

        return null;
    }
}