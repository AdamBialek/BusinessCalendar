package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Login;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginScreenController {

    private Connection connection;

    private Login login;

    private MainScreenController mainScreenController;

    private CRUD crud;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField loginContent;

    @FXML
    private TextField passwordContent;

    @FXML
    private void goBackToSelectScreen() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void attemptLogin() throws IOException, SQLException {
        String loginToCheck = loginContent.getText();
        String passToCheck = passwordContent.getText();
        boolean check = crud.loginExist(loginToCheck, passToCheck);
        System.out.println(check);
        if(check){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
            FlowPane anchorPane = fxmlLoader.load();
            mainScreenController.setScreen(anchorPane);
        } else {
        }

    }

    @FXML
    public void initialize() {
        connection=new SQLConnection().getConnection();
        crud = new CRUD(connection);
        login=new Login();
    }
}
