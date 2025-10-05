package com.smartcampus.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView extends VBox {

    private final ComboBox<String> roleComboBox;
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final Button loginButton;
    private final Button signupButton;
    private final Label statusLabel;

    public LoginView() {
        setPadding(new Insets(20));
        setSpacing(15);
        setAlignment(Pos.CENTER);
        getStyleClass().add("login-signup-container");

        Label title = new Label("Smart Campus Login");
        title.getStyleClass().add("login-signup-title");

        Label roleLabel = new Label("Select Role:");
        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Faculty", "Student");
        roleComboBox.setValue("Student"); // Default selection

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

        signupButton = new Button("Sign Up");
        signupButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");

        getChildren().addAll(title, roleLabel, roleComboBox, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, signupButton, statusLabel);
    }

    public ComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getSignupButton() {
        return signupButton;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}
