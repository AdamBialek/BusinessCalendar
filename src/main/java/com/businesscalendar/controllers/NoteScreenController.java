package com.businesscalendar.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class NoteScreenController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField textContext;

    @FXML
    public void saveNote() {}

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }
}
