package com.businesscalendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Note {

    private LocalDate noteDate;

    private LocalDate todayDate;

    private LocalDate firstDay;

    private Month thisMonth;

    private int daysMonth;

    int weekdayAtStartOfMonth;

    public int getDaysMonth() {
        return daysMonth;
    }

    public int getWeekdayAtStartOfMonth() {
        return weekdayAtStartOfMonth;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public Month getThisMonth() {
        return thisMonth;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public void setThisMonth(Month thisMonth) {
        this.thisMonth = thisMonth;
    }

    public GridPane disable(int buttonNo, boolean choice, GridPane gridPane){
        String idToCompare = new StringBuilder("dayCard").append(buttonNo).toString();
        for(Node n : gridPane.getChildren()){
            if(n instanceof Button){
                if(((Button)n).getId().equals(idToCompare)){
                    ((Button)n).setText("");
                    ((Button)n).setDisable(choice);
                }
            }
        }
        return gridPane;
    }

    public GridPane setBtnTxt(int buttonNo, int day, GridPane gridPane){
        String idToCompare = new StringBuilder("dayCard").append(buttonNo).toString();
        for(Node n : gridPane.getChildren()){
            if(n instanceof Button){
                if(((Button)n).getId().equals(idToCompare)){
                    ((Button)n).setDisable(false);
                    ((Button)n).setText(String.valueOf(day));
                }
            }
        }
        return gridPane;
    }

    public GridPane setDatesToWindows(int daysMonth, Month month, int weekdayAtStartOfMonth,GridPane gridPane){
        for(int i=0; i<weekdayAtStartOfMonth-1; i++){
            gridPane=disable(i,true,gridPane);
        }
        int day=0;
        for(int i=weekdayAtStartOfMonth-1; i<daysMonth+weekdayAtStartOfMonth;i++){
            day++;
            gridPane=setBtnTxt(i,day,gridPane);
        }
        for (int i=weekdayAtStartOfMonth+daysMonth-1;i<42;i++){
            gridPane=disable(i,true,gridPane);
        }
        return gridPane;
    }

    public void previous(){
        thisMonth=thisMonth.minus(1);
        todayDate=todayDate.minusMonths(1);
    }

    public void next(){
        thisMonth = thisMonth.plus(1);
        todayDate=todayDate.plusMonths(1);
    }

    public FXMLLoader onClick(GridPane gridPane, FXMLLoader fxmlLoader) throws IOException {

        for(Node n : gridPane.getChildren()){
            if(n instanceof Button){
                if(((Button)n).isPressed()){
                    String day=((Button)n).getText();
                    noteDate = LocalDate.of(todayDate.getYear(),todayDate.getMonth(),Integer.valueOf(day));
                    Login login = new Login();
                    login.setLocalDate(noteDate);
                    List<Note> dayList = new LinkedList<>();
                    for (Note note: login.getNoteList()
                    ) {
                        if(note.getDate().equals(login.getLocalDate())){
                            dayList.add(note);
                        }
                    }
                    login.setNotesOfDay(dayList);
                    return fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ChooseScreen.fxml"));
                }
            }
        }
        return fxmlLoader;
    }

    public GridPane setDatesToDisplayInWindows(GridPane gridPane){
        int thisYear = todayDate.getYear();
        firstDay = LocalDate.of(thisYear,thisMonth,1);
        boolean leapYear = todayDate.isLeapYear();
        daysMonth = thisMonth.length(leapYear);
        DayOfWeek firstOfMonth =firstDay.getDayOfWeek();
        weekdayAtStartOfMonth=firstOfMonth.getValue();
        return gridPane;
    }

//    ******************MODEL NOTKI***************:

    private int noteID;

    private String note;

    private LocalDate date;

    private int userID;

    public int getNoteID() {
        return noteID;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    public Note(){}

    public Note(int noteID, String note, LocalDate date, int userID) {
        this.noteID = noteID;
        this.note = note;
        this.date = date;
        this.userID = userID;
    }
}
