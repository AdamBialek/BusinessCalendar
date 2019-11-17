package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.sql.Connection;

public class ChooseScreenController {

    private Connection connection;

    private MainScreenController mainScreenController;

    private CRUD crud;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    public void previousNote() {}

    @FXML
    public void nextNote() {}

    @FXML
    public void enterNote() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/NoteScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        NoteScreenController noteScreenController = fxmlLoader.getController();
        noteScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void addNote() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/NoteScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        NoteScreenController noteScreenController = fxmlLoader.getController();
        noteScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        FlowPane anchorPane = fxmlLoader.load();
        MenuScreenController menuScreenController = fxmlLoader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void initialize() {
        connection=new SQLConnection().getConnection();
        crud = new CRUD(connection);
    }
}
