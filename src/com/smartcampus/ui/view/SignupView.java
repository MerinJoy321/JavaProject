package com.smartcampus.ui.view;

import com.smartcampus.data.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SignupView extends VBox {

    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<User.Role> roleComboBox;
    private Button signUpButton;
    private Label loginLabel;
    private Label errorLabel;

    public interface SignUpCallback {
        void onSignUpSuccess(User user);
        void onLoginClicked();
    }

    private SignUpCallback callback;

    public SignupView(SignUpCallback callback) {
        this.callback = callback;
        initializeUI();
    }

    private void initializeUI() {
        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(20));
        getStyleClass().add("signup-view");

        Label titleLabel = new Label("Smart Campus Sign Up");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(User.Role.STUDENT, User.Role.FACULTY, User.Role.ADMIN);
        roleComboBox.setValue(User.Role.STUDENT);

        signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> handleSignUp());

        loginLabel = new Label("Already have an account? Login");
        loginLabel.setOnMouseClicked(e -> callback.onLoginClicked());

        errorLabel = new Label();

        getChildren().addAll(titleLabel, usernameField, passwordField, roleComboBox, signUpButton, loginLabel, errorLabel);
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User.Role role = roleComboBox.getValue();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill all fields");
            return;
        }

        // Mock sign up - just create user
        User user = new User(4, username, password, role); // ID 4 for new user
        callback.onSignUpSuccess(user);
    }
}
