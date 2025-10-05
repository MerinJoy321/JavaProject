package com.smartcampus.app;

import com.smartcampus.data.model.User;
import com.smartcampus.infrastructure.persistence.config.DatabaseInitializer;
import com.smartcampus.ui.view.LoginView;
import com.smartcampus.ui.view.SignUpView;
import com.smartcampus.ui.view.MainDashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartCampusApp extends Application {

    private Stage primaryStage;
    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Smart Campus Navigation & Notification System");

        // Initialize database
        DatabaseInitializer.initializeDatabase();

        showLoginView();
        primaryStage.show();
    }

    private void showLoginView() {
        LoginView loginView = new LoginView(new LoginView.LoginCallback() {
            public void onLoginSuccess(User user) {
                currentUser = user;
                showMainDashboard();
            }

            public void onSignUpClicked() {
                showSignUpView();
            }
        });

        Scene loginScene = new Scene(loginView, 400, 500);
        loginScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(loginScene);
    }

    private void showSignUpView() {
        SignUpView signUpView = new SignUpView(new SignUpView.SignUpCallback() {
            public void onSignUpSuccess(User user) {
                currentUser = user;
                showMainDashboard();
            }

            public void onLoginClicked() {
                showLoginView();
            }
        });

        Scene signUpScene = new Scene(signUpView, 400, 550);
        signUpScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(signUpScene);
    }

    private void showMainDashboard() {
        MainDashboard mainDashboard = new MainDashboard(currentUser, () -> {
            currentUser = null;
            showLoginView();
        });
        Scene dashboardScene = new Scene(mainDashboard, 1024, 768);
        dashboardScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(dashboardScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
