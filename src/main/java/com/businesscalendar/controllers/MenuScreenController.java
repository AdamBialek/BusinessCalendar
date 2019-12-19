package com.businesscalendar.controllers;

import com.businesscalendar.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MenuScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    //*****************************WSZYSTKO DLA KALENDARZA*********************************:

    private Note note;

    @FXML
    private Label calendarMonthLabel;

    @FXML
    private GridPane calendarDaysGridPane;

    public GridPane getCalendarDaysGridPane() {
        return calendarDaysGridPane;
    }

    public void setCalendarDaysGridPane(GridPane calendarDaysGridPane) {
        this.calendarDaysGridPane = calendarDaysGridPane;
    }

    @FXML
    public void prevMonthSwitch(){
        note.previous();
        setCalendarDaysGridPaneContent();
    }

    @FXML
    public void nextMonthSwitch(){
        note.next();
        setCalendarDaysGridPaneContent();
    }

    @FXML
    public void onDateClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        fxmlLoader = note.onClick(getCalendarDaysGridPane(),fxmlLoader);
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void setCalendarDaysGridPaneContent(){
        GridPane gridPane=note.setDatesToDisplayInWindows(getCalendarDaysGridPane());
        setCalendarDaysGridPane(note.setDatesToWindows(note.getDaysMonth(),note.getThisMonth(),note.getWeekdayAtStartOfMonth(),gridPane));
        calendarMonthLabel.setText(note.getThisMonth().toString()+"\n"+note.getTodayDate().getYear());
    }

    //*****************************WSZYSTKO DLA POGODY*********************************:

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

    //*****************************WSZYSTKO DLA KALKULATORA*********************************:



    //*****************************WSZYSTKO DLA NEWSÓW*********************************:



//    ***********FUNKCJONALNOSCI KONTA USERA***********:

    private Connection connection;

    private Login login;

    private CRUD crud;

    @FXML
    private void logOut() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void deleteAccount() throws SQLException {
        crud.deleteUserNotes();
        crud.deleteUser();
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void changeEmail() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChangeEmail.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChangeEmailController changeEmailController = fxmlLoader.getController();
        changeEmailController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    private void contactUs() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SendMail.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        SendMailController sendMailController = fxmlLoader.getController();
        sendMailController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    void initialize() {
//        *********KALENDARZ************:
        note=new Note();
        note.setTodayDate(LocalDate.now());
        note.setThisMonth(note.getTodayDate().getMonth());
        setCalendarDaysGridPaneContent();
//        *********POGODA*********:
        weather=new Weather();
//        ********NEWS API***********:

//        ********USUN USERA*********:
        connection=new SQLConnection().getConnection();
        crud=new CRUD(connection);
        login=new Login();
    }
}