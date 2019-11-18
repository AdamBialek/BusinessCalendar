package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private FlowPane mainFlowPane;

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
        mainFlowPane.getChildren().clear();
        mainFlowPane.getChildren().add(anchorPane);
    }

    public void setScreen(FlowPane flowPane){
        mainFlowPane.getChildren().clear();
        mainFlowPane.getChildren().add(flowPane);
    }
}
