package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class LoginScreenController {

    @FXML
    private TextField loginContent;

    @FXML
    private TextField passwordContent;


    @FXML
    private void goBackToSelectScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SelectScreen.fxml"));
        AnchorPane anchorPane;
        anchorPane = fxmlLoader.load();
    }

    @FXML
    private void attemptLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScreen.fxml"));
        AnchorPane anchorPane;
        anchorPane = fxmlLoader.load();
    }
}
