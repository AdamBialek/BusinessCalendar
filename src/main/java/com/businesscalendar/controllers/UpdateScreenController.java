package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Login;
import com.businesscalendar.Note;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UpdateScreenController {

    private Connection connection;

    private MainScreenController mainScreenController;

    private CRUD crud;

    private Login login;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    public TextArea displayText;

    @FXML
    public TextArea updateText;

    @FXML
    public void updateNote() throws SQLException, IOException {
        String updated=updateText.getText();
        crud.updateNote(updated,login.getNoteId());
        crud.getNotesById();
        List<Note> dayList = new LinkedList<>();
        Login login = new Login();
        for (Note note: login.getNoteList()
        ) {
            if(note.getDate().equals(login.getLocalDate())){
                dayList.add(note);
            }
        }
        login.setNotesOfDay(dayList);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(anchorPane);
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
        login=new Login();
        System.out.println(login.getNoteId());
        for (Note n: login.getNotesOfDay()) {
            if(login.getNoteId()==n.getNoteID()) {
                displayText.setText(n.getNote());
                updateText.setText(n.getNote());
            }
        }
    }
}
