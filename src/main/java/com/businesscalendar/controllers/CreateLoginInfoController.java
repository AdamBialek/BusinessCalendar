package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;


public class CreateLoginInfoController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField createLogin;

    @FXML
    private TextField createPassword;

    @FXML
    private void goBackToSelectScreen() throws IOException {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void attemptToRegister() throws IOException {
//        createLogin.disableProperty();
//        createPassword.disableProperty();
//        String login = createLogin.getText();
//        String password = createPassword.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        FlowPane anchorPane = fxmlLoader.load();
        mainScreenController.setScreen(anchorPane);
    }
}
