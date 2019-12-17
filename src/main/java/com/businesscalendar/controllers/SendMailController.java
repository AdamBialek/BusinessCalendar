package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Email;
import com.businesscalendar.Login;

import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class SendMailController {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    private Connection connection;

    private CRUD crud;

    private Login login;

    @FXML
    private TextField msgTitle;

    @FXML
    private TextArea msgContent;

    @FXML
    public void sendMsg() throws SQLException, IOException {
        String title = msgTitle.getText();
        String msg = msgContent.getText();
        int userId = login.getUserID();
        String email = crud.getEmailById(userId);
        Email.sendMessage("[Contact us] "+title, msg,"User email: "+email,"businesscalendarprompt@gmail.com");
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
        FlowPane anchorPane = fxmlLoader.load();
        MenuScreenController menuScreenController = fxmlLoader.getController();
        menuScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
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
    void initialize(){

        login=new Login();
        connection=new SQLConnection().getConnection();
        crud=new CRUD(connection);
    }
}
