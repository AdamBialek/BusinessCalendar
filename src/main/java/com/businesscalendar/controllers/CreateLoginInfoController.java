package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Email;
import com.businesscalendar.Login;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class CreateLoginInfoController {

    private MainScreenController mainScreenController;

    private Connection connection;

    private CRUD crud;

    private Login login;

    private Email email;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField createLogin;

    @FXML
    private PasswordField createPassword;

    @FXML
    private TextField enterEmail;

    @FXML
    private void goBackToSelectScreen() throws IOException {
        mainScreenController.loadMenuScreen();
    }

    @FXML
    private void attemptToRegister() throws IOException, SQLException {
        String loginToCheck = createLogin.getText();
        String password = createPassword.getText();

        boolean loginOK = login.checkLoginOrPassword(loginToCheck);

        if(loginOK & !loginToCheck.equals(password)){
            int loginAvail=crud.loginAvailability(loginToCheck);
            if(loginAvail==0){
                boolean passOK = login.checkLoginOrPassword(password);
                if(passOK){
                    crud.addLoginPass(loginToCheck,password);
                    crud.loginExist(loginToCheck,password);
                    crud.getNotesById();
                    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
                    FlowPane anchorPane = fxmlLoader.load();
                    MenuScreenController menuScreenController = fxmlLoader.getController();
                    menuScreenController.setMainScreenController(mainScreenController);
                    mainScreenController.setScreen(anchorPane);
                }
            }
        }
    }

    @FXML
    public void initialize(){
        connection=new SQLConnection().getConnection();
        crud = new CRUD(connection);
        email=new Email();
    }
}