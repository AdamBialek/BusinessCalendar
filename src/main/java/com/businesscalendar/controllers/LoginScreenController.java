package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Email;
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
        int attempt = crud.attemptNo(loginContent.getText());
        if(check && attempt<4){
            crud.getNotesById();
            crud.setAttempts(loginContent.getText());
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
                if(attempt==1){
                    loginMessage.setText("Wrong password\n2 attempts remaining");
                } else if(attempt==2){
                    loginMessage.setText("Wrong password\n1 attempts remaining");
                } else if(attempt>2){
                    loginMessage.setText("Account blocked.\nUse Forgot Password? button");
                }
            }
        }
    }

    @FXML
    private void forgotPassword() throws SQLException {
        String loginToCheck=loginContent.getText();
        int check = crud.loginAvailability(loginToCheck);
        if(check==1){
            crud.setAttempts(loginContent.getText());
            String email = crud.getEmail(loginToCheck);
            String password=crud.passwordReminder(loginToCheck);
            Email.sendMessage("Password reminder","Your password is: "+password,"The Business Calendar Team",email);
            loginMessage.setText("Check your email");
        }  else if(loginContent.getText().length()==0){
            loginMessage.setText("Input your username");
        } else {
            loginMessage.setText("Input existing username");
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