package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private void enterLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        LoginScreenController loginScreenController = fxmlLoader.getController();
        loginScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);

    }

    @FXML
    private void enterRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/CreateLoginInfo.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        CreateLoginInfoController createLoginInfoController = fxmlLoader.getController();
        createLoginInfoController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }
}
