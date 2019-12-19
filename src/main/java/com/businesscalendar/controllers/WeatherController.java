package com.businesscalendar.controllers;

import com.businesscalendar.Weather;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WeatherController {

    private Weather weather;

    @FXML
    private Label weatherDataInput;

    @FXML
    private TextField cityName;

    @FXML
    public void checkWeatherButton() {
        String weatherInfo=weather.getWeatherInfo(cityName.getText());
        cityName.setText("");
        weatherDataInput.setText(weatherInfo);
    }

    @FXML
    public void initialize(){
        weather=new Weather();
    }
}
