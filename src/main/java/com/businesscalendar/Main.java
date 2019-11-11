package com.businesscalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
        FlowPane flowPane = fxmlLoader.load();

        primaryStage.setMinWidth(625);
        primaryStage.setMinHeight(200);

        Scene scene=new Scene(flowPane);



        primaryStage.setScene(scene);
        primaryStage.setTitle("Business Calendar");
        primaryStage.show();
    }
}
