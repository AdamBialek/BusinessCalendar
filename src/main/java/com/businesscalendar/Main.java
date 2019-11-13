package com.businesscalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        launch(args);
//        Connection connection = new SQLConnection().getConnection();
//        CRUD crud = new CRUD(connection);
//        int result = crud.loginAvailability("testUser");
//        System.out.println(result);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        primaryStage.setMinWidth(307);
        primaryStage.setMinHeight(400);

        Scene scene=new Scene(anchorPane);



        primaryStage.setScene(scene);
        primaryStage.setTitle("Business Calendar");
        primaryStage.show();
    }
}
