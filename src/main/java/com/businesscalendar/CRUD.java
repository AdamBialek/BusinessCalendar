package com.businesscalendar;

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

    public String passwordReminder(String login) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT Password FROM Users\n" +
                "WHERE Login='").append(login+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        int count=0;
        String password="";
        while (rs.next()){
            count++;
            password=rs.getString("Password");
        }
        return password;
    }

    public String getEmail(String login) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT Email FROM Users\n" +
                "WHERE Login='").append(login+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        int count=0;
        String email="";
        while (rs.next()){
            count++;
            email=rs.getString("Email");
        }
        return email;
    }

    public String getEmailById(int id) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT Email FROM Users\n" +
                "WHERE UserID=").append(id).toString();

        ResultSet rs = statement.executeQuery(select);

        int count=0;
        String email="";
        while (rs.next()){
            count++;
            email=rs.getString("Email");
        }
        return email;
    }

    public void addLoginPass(String login,String pass, String email) throws SQLException {
        Statement statement = connection.createStatement();

        String insert = new StringBuilder("INSERT INTO Users (Login, Password, Email, Attempts)\nVALUES ('").append(login+"','").
                append(pass+"','").append(email+"','").append(0+"');").toString();

        statement.executeUpdate(insert);
    }

    public int attemptNo(String login) throws SQLException {
        Statement statement = connection.createStatement();

        String select = new StringBuilder("SELECT Attempts FROM Users\n" +
                "WHERE Login='").append(login+"'").toString();

        ResultSet rs = statement.executeQuery(select);

        int attempts=0;
        while (rs.next()){
            attempts=rs.getInt("Attempts");
            attempts++;
        }

        Statement statement1 = connection.createStatement();

        String insert = new StringBuilder("UPDATE Users SET Attempts=").append(attempts+"\n").append("WHERE Login='"+login+"';").toString();
        statement1.executeUpdate(insert);

        return attempts;
    }

    public void setAttempts(String login) throws SQLException {
        Statement statement1 = connection.createStatement();

        String insert = new StringBuilder("UPDATE Users SET Attempts=").append(0+"\n").append("WHERE Login='"+login+"';").toString();
        statement1.executeUpdate(insert);
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

    public void deleteUser() throws SQLException {
        Statement statement = connection.createStatement();
        String update = new StringBuilder("DELETE FROM Users WHERE rowid='").append(loginData.getUserID()+"'").toString();
        statement.executeUpdate(update);
    }

//    *********************WSZYSTKO DLA NOTATEK***************************:

    public void addNote(String note, LocalDate noteDate, int userID) throws SQLException {
        Statement statement = connection.createStatement();
        String insert = new StringBuilder("INSERT INTO notes (Note, Date, UserID)\nVALUES ('").append(note+"','"+noteDate.getYear()+"-"+noteDate.getMonth().getValue()+"-"+noteDate.getDayOfMonth()+"','"+userID+"')").toString();
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
            String dateString = rs.getString("Date");
            System.out.println(dateString);
            int year = Integer.valueOf(dateString.substring(0,4));
            int month=0;
            int day= 0;
            try {
                month = Integer.valueOf(dateString.substring(5,7));
            } catch (NumberFormatException ex) {
                month = Integer.valueOf(dateString.substring(5,6));
            }
            try {
                day = Integer.valueOf(dateString.substring(dateString.length()-2));
                if(day<0){
                    day=day*-1;
                }
            } catch (NumberFormatException ex) {
                day = Integer.valueOf(dateString.substring(dateString.length()-1));
            }
            System.out.println(year+" "+month+" "+day);
            LocalDate date=LocalDate.of(year,month,day);
            int userId = rs.getInt("UserID");
            Note noteObject = new Note(noteId, note, date, userId);
            notes.add(noteObject);
        }
        loginData.setNoteList(notes);
    }

    public void updateNote(String updatedText, int noteId) throws SQLException {
        Statement statement = connection.createStatement();
        String update = new StringBuilder("UPDATE notes\nSET Note='").append(updatedText+"'\nWHERE NoteID='"+noteId+"'").toString();
        statement.executeUpdate(update);
    }

    public void deleteNote(int noteId) throws SQLException {
        Statement statement = connection.createStatement();
        String update = new StringBuilder("DELETE FROM notes WHERE NoteID='").append(noteId+"'").toString();
        statement.executeUpdate(update);
    }

    public void deleteUserNotes() throws SQLException {
        Statement statement = connection.createStatement();
        String update = new StringBuilder("DELETE FROM notes WHERE UserID='").append(loginData.getUserID()+"'").toString();
        statement.executeUpdate(update);
    }
}