package com.smartcampus.app;

import com.smartcampus.ui.view.LoginView;
import com.smartcampus.ui.view.SignupView;
import com.smartcampus.ui.view.MainDashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class SmartCampusApp extends Application {

    private static final Map<String, String> users = new HashMap<>(); // username -> password:role

    private Stage primaryStage;
    private String currentRole;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Smart Campus Navigation & Notification System");

        showLoginView();
        primaryStage.show();
    }

    private void showLoginView() {
        LoginView loginView = new LoginView();
        Scene loginScene = new Scene(loginView, 400, 500);
        loginScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        loginView.getLoginButton().setOnAction(e -> {
            String username = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();
            String role = loginView.getRoleComboBox().getValue();

            if (authenticate(username, password, role)) {
                currentRole = role;
                showMainDashboard();
            } else {
                loginView.getStatusLabel().setText("Invalid credentials. Try again.");
            }
        });

        loginView.getSignupButton().setOnAction(e -> showSignupView());

        primaryStage.setScene(loginScene);
    }

    private void showSignupView() {
        SignupView signupView = new SignupView();
        Scene signupScene = new Scene(signupView, 400, 550);
        signupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        signupView.getBackToLoginButton().setOnAction(e -> showLoginView());

        signupView.getSignupButton().setOnAction(e -> {
            String username = signupView.getUsernameField().getText();
            String password = signupView.getPasswordField().getText();
            String role = signupView.getRoleComboBox().getValue();

            // Store the user
            users.put(username, password + ":" + role);
            currentRole = role;
            showMainDashboard();
        });

        primaryStage.setScene(signupScene);
    }

    private void showMainDashboard() {
        MainDashboard mainDashboard = new MainDashboard();
        Scene dashboardScene = new Scene(mainDashboard, 1024, 768);

        // Add modern stylesheet
        dashboardScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(dashboardScene);
    }

    private boolean authenticate(String username, String password, String role) {
        // Check signed up users first
        String stored = users.get(username);
        if (stored != null) {
            String[] parts = stored.split(":");
            return parts.length == 2 && parts[0].equals(password) && parts[1].equals(role);
        }
        // Fallback to hardcoded authentication for demo purposes
        // In a real app, this would check against a database
        if ("admin".equals(username) && "admin".equals(password) && "Admin".equals(role)) {
            return true;
        }
        if ("faculty".equals(username) && "faculty".equals(password) && "Faculty".equals(role)) {
            return true;
        }
        if ("student".equals(username) && "student".equals(password) && "Student".equals(role)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
