package com.smartcampus.app;

import com.smartcampus.data.model.User;
import com.smartcampus.ui.view.LoginView;
import com.smartcampus.ui.view.MainDashboard;
import com.smartcampus.ui.view.SignUpView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartCampusApp extends Application {

    private Stage primaryStage;
    private User loggedInUser;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Smart Campus Navigation & Notification System");

        showLoginView();
        primaryStage.show();
    }

    private void showLoginView() {
        LoginView loginView = new LoginView(new LoginView.LoginCallback() {
            @Override
            public void onLoginSuccess(User user) {
                loggedInUser = user;
                showMainDashboard();
            }

            @Override
            public void onSignUpClicked() {
                showSignUpView();
            }
        });
        Scene scene = new Scene(loginView, 400, 300);
        primaryStage.setScene(scene);
    }

    private void showSignUpView() {
        SignUpView signUpView = new SignUpView(new SignUpView.SignUpCallback() {
            @Override
            public void onSignUpSuccess(User user) {
                loggedInUser = user;
                showMainDashboard();
            }

            @Override
            public void onLoginClicked() {
                showLoginView();
            }
        });
        Scene scene = new Scene(signUpView, 400, 350);
        primaryStage.setScene(scene);
    }

    private void showMainDashboard() {
        MainDashboard mainDashboard = new MainDashboard(loggedInUser);
        Scene scene = new Scene(mainDashboard, 1024, 768);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
