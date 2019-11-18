package com.businesscalendar;

import com.businesscalendar.controllers.LoginScreenController;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CRUD {

    Connection connection;

    public CRUD(Connection connection){this.connection=connection;}

//    *************WSZYSTKO DLA UŻYTKOWNIKÓW***************:

    public int loginAvailability(String string) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT * FROM Users WHERE Login='").append(string+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        int count=0;
        while (rs.next()){
            count++;
        }
        return count;
    }

    public void addLoginPass(String login,String pass) throws SQLException {
        Statement statement = connection.createStatement();

        String insert = new StringBuilder("INSERT INTO Users (Login, Password)\nVALUES ('").append(login+"','").
                append(pass+"');").toString();

        statement.executeUpdate(insert);
    }

    Login loginData = new Login();

    public boolean loginExist(String login,String password) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT * FROM Users WHERE Login='").append(login+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();
        int counter=0;

        boolean checkLogin = false;
        boolean checkPass = false;
        boolean result = false;

        int id;
//        LoginScreenController loginScreenController=new LoginScreenController();


        while (rs.next()){
            counter++;
            for(int i=1;i<=columnCount; i++){

                id =(rs.getInt("UserID"));
                loginData.setUserID((id));

                if(rsmd.getColumnName(i).equals("Login")){
                    if(rs.getString(i).equals(login)){
                        checkLogin = true;
                    }
                }
                if(rsmd.getColumnName(i).equals("Password")){
                    if(rs.getString(i).equals(password)){
                        checkPass = true;}
                }
            }
        }




        if(checkLogin & checkPass & counter==1){
            result=true;
        }
        return result;
    }

//    *********************WSZYSTKO DLA NOTATEK***************************:

    private Note note;

    public void addNote(String note, LocalDate noteDate, int userID) throws SQLException {
        Statement statement = connection.createStatement();
        String insert = new StringBuilder("INSERT INTO notes (Note, Date, UserID)\nVALUES ('").append(note+"','"+noteDate+"','"+userID+"')").toString();
        statement.executeUpdate(insert);
    }

    List<Note> notes = new LinkedList<>();

    public void getNotesById() throws SQLException {
        Statement statement = connection.createStatement();

        int userID = loginData.getUserID();
        String select = new StringBuilder("SELECT * FROM notes WHERE UserID='").append(userID+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        ResultSetMetaData rsmd = rs.getMetaData();

        while (rs.next()){
            int noteId = rs.getInt("NoteID");
            String note = rs.getString("Note");
            Date date = rs.getDate("Date");
            int userId = rs.getInt("UserID");
            Note noteObject = new Note(noteId, note, date.toLocalDate(), userId);
            notes.add(noteObject);
        }
        loginData.setNoteList(notes);
    }

    public void updateNote(String updatedText, int noteId) throws SQLException {
        Statement statement = connection.createStatement();
        String update = new StringBuilder("UPDATE notes\nSET Note='").append(updatedText+"'\nWHERE NoteID='"+noteId+"'").toString();
        statement.executeUpdate(update);
    }
}
