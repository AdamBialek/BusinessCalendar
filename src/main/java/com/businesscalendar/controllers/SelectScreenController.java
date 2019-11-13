package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectScreenController {

    @FXML
    private void enterLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        setAnchorPane(anchorPane);
    }

    @FXML
    private void enterRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/CreateLoginInfo.fxml"));
        AnchorPane anchorPane;
        anchorPane = fxmlLoader.load();
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(anchorPane);
    }

    @FXML
    public void initialize() throws IOException {

    }
}
