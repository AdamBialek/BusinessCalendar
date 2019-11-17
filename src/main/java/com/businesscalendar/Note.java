package com.businesscalendar;

import java.time.LocalDate;

public class Note {

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

    public int getUserID() {
        return userID;
    }

    public Note(int noteID, String note, LocalDate date, int userID) {
        this.noteID = noteID;
        this.note = note;
        this.date = date;
        this.userID = userID;
    }
}
