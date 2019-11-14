package com.businesscalendar;

import java.sql.*;

public class CRUD {

    Connection connection;

    public CRUD(Connection connection){this.connection=connection;}

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


        while (rs.next()){
            counter++;
            for(int i=1;i<=columnCount; i++){
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
}
