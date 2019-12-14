package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Login;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginScreenController {

    private Login login;

    private Connection connection;

    private MainScreenController mainScreenController;

    private CRUD crud;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private Label loginMessage;

    @FXML
    private TextField loginContent;

    @FXML
    private PasswordField passwordContent;

    @FXML
    private void goBackToSelectScreen() {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void attemptLogin() throws IOException, SQLException {
        boolean check = crud.loginExist(loginContent.getText(), passwordContent.getText());
        crud.getNotesById();
        if(check){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
            FlowPane anchorPane = fxmlLoader.load();
            MenuScreenController menuScreenController = fxmlLoader.getController();
            menuScreenController.setMainScreenController(mainScreenController);
            mainScreenController.setScreen(anchorPane);
        } else if(crud.loginAvailability(loginContent.getText())==0) {
            loginMessage.setText("Couldn't find your account");
        } else if(crud.loginAvailability(loginContent.getText())==1){
            if(passwordContent.getText().length()==0){
                loginMessage.setText("Enter a password");
            } else if(!check){
                loginMessage.setText("Wrong password");
            }
        }
    }

    @FXML
    public void initialize() {
        connection=new SQLConnection().getConnection();
        crud = new CRUD(connection);
        login=new Login();
        loginMessage.setText("");
    }
}