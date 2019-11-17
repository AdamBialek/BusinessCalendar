package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Login;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class NoteScreenController {

    private Connection connection;

    private MainScreenController mainScreenController;

    private CRUD crud;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField textContext;

    @FXML
    public void saveNote() throws SQLException {
        Login login = new Login();
        String input = textContext.getText();
        crud.addNote(input,login.getLocalDate(),login.getUserID());
        textContext.setText("");
    }

    @FXML
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void initialize(){
        connection=new SQLConnection().getConnection();
        crud = new CRUD(connection);
    }
}
