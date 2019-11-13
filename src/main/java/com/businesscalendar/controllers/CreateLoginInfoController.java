package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class CreateLoginInfoController {

    @FXML
    private TextField createLogin;

    @FXML
    private TextField createPassword;

    @FXML
    private void goBackToSelectScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SelectScreen.fxml"));
        AnchorPane anchorPane;
        anchorPane = fxmlLoader.load();
    }

    @FXML
    private void attemptToRegister() {
        createLogin.disableProperty();
        createPassword.disableProperty();
        String login = createLogin.getText();
        String password = createPassword.getText();
    }
}
