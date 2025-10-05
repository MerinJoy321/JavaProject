package com.smartcampus.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class SignupView extends VBox {
    private ComboBox<String> roleComboBox;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button signupButton;
    private Button backToLoginButton;

    public SignupView() {
        setPadding(new Insets(20));
        setSpacing(15);
        setAlignment(Pos.CENTER);
        getStyleClass().add("login-signup-container");

        Label titleLabel = new Label("Sign Up");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Faculty", "Student");
        roleComboBox.setValue("Student");
        roleComboBox.setMaxWidth(200);

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);

        signupButton = new Button("Sign Up");
        signupButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        backToLoginButton = new Button("Back to Login");
        backToLoginButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        getChildren().addAll(titleLabel, new Label("Role:"), roleComboBox,
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                signupButton, backToLoginButton);
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

    public Button getSignupButton() {
        return signupButton;
    }

    public Button getBackToLoginButton() {
        return backToLoginButton;
    }
}
