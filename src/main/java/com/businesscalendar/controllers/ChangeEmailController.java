package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Email;
import com.businesscalendar.Login;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ChangeEmailController {

    private MainScreenController mainScreenController;

    private Login login;

    private CRUD crud;

    private Connection connection;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private TextField currentEmail;

    @FXML
    private TextField newEmail;

    @FXML
    private Label newEmailLabel;

    @FXML
    public void updateEmail() throws SQLException, IOException {
        if(login.validateEmail(newEmail.getText())){
            crud.updateEmail(newEmail.getText(),login.getUserID());
            Email.sendMessage("Email address updated","You updated your email address","The Business Calendar Team",newEmail.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
            FlowPane anchorPane = fxmlLoader.load();
            MenuScreenController menuScreenController = fxmlLoader.getController();
            menuScreenController.setMainScreenController(mainScreenController);
            mainScreenController.setScreen(anchorPane);
        } else {
            newEmailLabel.setText(newEmailLabel.getText()+"Provide valid email address");
        }
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        FlowPane anchorPane = fxmlLoader.load();
        MenuScreenController menuScreenController = fxmlLoader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
    }

    @FXML
    public void initialize() throws SQLException {
        login=new Login();
        connection=new SQLConnection().getConnection();
        crud=new CRUD(connection);
        int userID = login.getUserID();
        String email = crud.getEmailById(userID);
        currentEmail.setText(email);
    }
}
