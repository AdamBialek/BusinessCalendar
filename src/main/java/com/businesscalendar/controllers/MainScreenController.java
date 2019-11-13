package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {
        loadMenuScreen();
    }

    public void loadMenuScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SelectScreen.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SelectScreenController selectScreenController = fxmlLoader.getController();
        selectScreenController.setMainScreenController(this);
        setScreen(anchorPane);
    }

    public void setScreen(AnchorPane anchorPane){
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(anchorPane);
    }

    public void setScreen(FlowPane flowPane){
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(flowPane);
    }
}
