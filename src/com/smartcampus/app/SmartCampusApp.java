package com.smartcampus.app;

import com.smartcampus.ui.view.MainDashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartCampusApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Campus Navigation & Notification System");

        MainDashboard mainDashboard = new MainDashboard();
        Scene scene = new Scene(mainDashboard, 1024, 768);
        
        // Optional: Add a stylesheet for a more polished look
        // scene.getStylesheets().add(getClass().getResource("/com/smartcampus/ui/resource/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
