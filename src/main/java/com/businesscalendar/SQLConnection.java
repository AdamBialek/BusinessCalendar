package com.businesscalendar;

import java.sql.*;

public class SQLConnection {
    private Connection connection;

    public Connection connectionToSQLServer() {
        String url = "jdbc:sqlite:usersandnotes.db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Connection getConnection() {
        return (connection != null) ? connection : connectionToSQLServer();
    }
}