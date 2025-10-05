package com.smartcampus.app;

<<<<<<< HEAD
import com.smartcampus.data.model.User;
import com.smartcampus.infrastructure.persistence.config.DatabaseInitializer;
import com.smartcampus.ui.view.LoginView;
import com.smartcampus.ui.view.SignUpView;
=======
>>>>>>> 8872f22dce8baf1b23b7bab30c4e3b26fcbbb6e5
import com.smartcampus.ui.view.MainDashboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartCampusApp extends Application {

<<<<<<< HEAD
    private Stage primaryStage;
    private User currentUser;

=======
>>>>>>> 8872f22dce8baf1b23b7bab30c4e3b26fcbbb6e5
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Campus Navigation & Notification System");

<<<<<<< HEAD
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
        MainDashboard mainDashboard = new MainDashboard(currentUser);
        Scene dashboardScene = new Scene(mainDashboard, 1024, 768);
        dashboardScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(dashboardScene);
=======
        MainDashboard mainDashboard = new MainDashboard();
        Scene scene = new Scene(mainDashboard, 1024, 768);
        
        // Optional: Add a stylesheet for a more polished look
        // scene.getStylesheets().add(getClass().getResource("/com/smartcampus/ui/resource/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
>>>>>>> 8872f22dce8baf1b23b7bab30c4e3b26fcbbb6e5
    }

    public static void main(String[] args) {
        launch(args);
    }
}
