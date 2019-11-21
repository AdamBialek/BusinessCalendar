package com.businesscalendar.controllers;

import com.businesscalendar.CRUD;
import com.businesscalendar.Login;
import com.businesscalendar.Note;
import com.businesscalendar.SQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChooseScreenController {

    private Connection connection;

    private MainScreenController mainScreenController;

    private CRUD crud;

    private Login login;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private Button noteText;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    public void previousNote() {
        for (int i=login.getNotesOfDay().size()-1; i>0;i--){
            if(login.getNoteId()==login.getNotesOfDay().get(i).getNoteID()){
                i=i-1;
                noteText.setText(String.valueOf(i+1)+". "+login.getNotesOfDay().get(i).getNote());
                login.setNoteId(login.getNotesOfDay().get(i).getNoteID());
                nextButton.setDisable(false);
                if(i==0){
                    prevButton.setDisable(true);
                }
                break;
            }
        }
    }

    @FXML
    public void nextNote() {

        for (Note n: login.getNotesOfDay()
             ) {
            if(n.getNoteID()>login.getNoteId()){
                int number=Integer.valueOf(noteText.getText().substring(0,1));
                number=number+1;
                noteText.setText(number+". "+n.getNote());
                login.setNoteId(n.getNoteID());
                int size = login.getNotesOfDay().size()-1;
                prevButton.setDisable(false);
                if(login.getNotesOfDay().get(size).getNoteID()==login.getNoteId()){
                    nextButton.setDisable(true);
                }
                break;
            }
        }
    }

    @FXML
    public void enterNote() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/UpdateScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        UpdateScreenController updateScreenController = fxmlLoader.getController();
        updateScreenController.setMainScreenController(mainScreenController);
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
    public void deleteNote() throws SQLException, IOException {
        prevButton.setDisable(true);
        nextButton.setDisable(true);
        int noteId = login.getNoteId();
        crud.deleteNote(noteId);
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
        login.getNotesOfDay();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ChooseScreenController chooseScreenController = fxmlLoader.getController();
        chooseScreenController.setMainScreenController(mainScreenController);
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
        login=new Login();
        if(login.getNotesOfDay().size()>0){
            Note initial=login.getNotesOfDay().get(0);
            login.setNoteId(initial.getNoteID());
            noteText.setText("1. "+initial.getNote());
            if(login.getNotesOfDay().size()==1){
                nextButton.setDisable(true);
            }
        } else {
            noteText.setText("No notes for this day");
            noteText.setDisable(true);
            nextButton.setDisable(true);
        }
    }
}