package com.smartcampus.ui.view;

import com.smartcampus.data.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView extends VBox {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label signUpLabel;
    private Label errorLabel;

    public interface LoginCallback {
        void onLoginSuccess(User user);
        void onSignUpClicked();
    }

    private LoginCallback callback;

    public LoginView(LoginCallback callback) {
        this.callback = callback;
        initializeUI();
    }

    private void initializeUI() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setPadding(new Insets(20));

        Label titleLabel = new Label("Smart Campus Login");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());

        signUpLabel = new Label("Don't have an account? Sign Up");
        signUpLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");
        signUpLabel.setOnMouseClicked(e -> callback.onSignUpClicked());

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, signUpLabel, errorLabel);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Mock authentication
        if ("admin".equals(username) && "admin".equals(password)) {
            User user = new User(1, username, password, User.Role.ADMIN);
            callback.onLoginSuccess(user);
        } else if ("faculty".equals(username) && "faculty".equals(password)) {
            User user = new User(2, username, password, User.Role.FACULTY);
            callback.onLoginSuccess(user);
        } else if ("student".equals(username) && "student".equals(password)) {
            User user = new User(3, username, password, User.Role.STUDENT);
            callback.onLoginSuccess(user);
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }
}
