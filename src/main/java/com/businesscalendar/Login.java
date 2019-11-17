package com.businesscalendar;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    private static int userID;

    private static LocalDate localDate;

    private static List<Note> noteList;

    private static List<Note> notesOfDay;

    private static int noteId;

    public List<Note> getNotesOfDay() {
        return notesOfDay;
    }

    public void setNotesOfDay(List<Note> notesOfDay) {
        Login.notesOfDay = notesOfDay;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        Login.noteId = noteId;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        Login.noteList = noteList;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        Login.localDate = localDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        Login.userID = userID;
    }

    public static boolean checkLoginOrPassword(String login){
        String regex = "[A-Z]{1,}";
        String regex2 = "[0-9]{1,}";
        String regex3 = "[a-z]{1,}";

        Pattern pattern1 = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher1 = pattern1.matcher(login);
        Matcher matcher2 = pattern2.matcher(login);
        Matcher matcher3 = pattern3.matcher(login);
        if(login.length()>7 & login.length()<16 & matcher1.find()
                & matcher2.find() & matcher3.find() & !login.contains(" ")){
            return true;
        } else {
            return false;
        }
    }
}
