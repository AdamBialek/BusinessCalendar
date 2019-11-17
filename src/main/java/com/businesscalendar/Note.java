package com.businesscalendar;

import java.time.LocalDate;

public class Note {

    private int noteID;

    private String note;

    private LocalDate date;

    private int userID;

    public Note(int noteID, String note, LocalDate date, int userID) {
        this.noteID = noteID;
        this.note = note;
        this.date = date;
        this.userID = userID;
    }
}
