package com.smartcampus.ui.view;

import com.smartcampus.data.CampusDataService;
import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class MainDashboard extends BorderPane {

    private final MapView mapView;
    private final List<Building> buildings;
    private ComboBox<Building> buildingComboBox;
    private final User user;

    public MainDashboard(User user) {
        this.user = user;
        this.buildings = CampusDataService.getCampusBuildings();
        this.mapView = new MapView();

        // Initialize buildingComboBox
        this.buildingComboBox = new ComboBox<>();
        this.buildingComboBox.setItems(FXCollections.observableArrayList(buildings));
        this.buildingComboBox.setMaxWidth(Double.MAX_VALUE);
        this.buildingComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldBuilding, newBuilding) -> {
            if (newBuilding != null) {
                mapView.renderBuilding(newBuilding);
            }
        });

        // --- Navigation Bar ---
        HBox navBar = new HBox(10);
        navBar.setPadding(new Insets(10));
        navBar.setStyle("-fx-background-color: #72a1d0ff;");

        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(e -> {
            // Clear selection and reset map
            this.buildingComboBox.getSelectionModel().clearSelection();
            mapView.renderBuilding(null);
        });

        Button aboutBtn = new Button("About");
        aboutBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Smart Campus Navigation & Notification System");
            alert.setContentText("Version 1.0\nDeveloped for efficient campus navigation.");
            alert.showAndWait();
        });

        Button servicesBtn = new Button("Services");
        servicesBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Services");
            alert.setHeaderText("Our Services");
            alert.setContentText("Campus navigation, building information, path finding.");
            alert.showAndWait();
        });

        Button contactBtn = new Button("Contact");
        contactBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Contact");
            alert.setHeaderText("Contact Us");
            alert.setContentText("Email: info@smartcampus.com\nPhone: 123-456-7890");
            alert.showAndWait();
        });

        navBar.getChildren().addAll(homeBtn, aboutBtn, servicesBtn, contactBtn);

        // --- Top Header ---
        Label title = new Label("Smart Campus Navigation");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setPadding(new Insets(10));

        Label userLabel = new Label("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
        userLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        userLabel.setPadding(new Insets(0, 10, 10, 10));

        VBox topBox = new VBox();
        topBox.getChildren().addAll(navBar, title, userLabel);
        this.setTop(topBox);
        BorderPane.setAlignment(topBox, Pos.CENTER);

        // --- Center Map View ---
        this.setCenter(mapView);
        BorderPane.setMargin(mapView, new Insets(10));

        // --- Left Control Panel (Building Selector) ---
        setupControlPanel();

        // Initial render
        mapView.renderBuilding(null);
    }

    private void setupControlPanel() {
        VBox controlPanel = new VBox(20);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setPrefWidth(250);
        controlPanel.setStyle("-fx-background-color: #e9ecef;");

        Label panelTitle = new Label("Campus Explorer");
        panelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        controlPanel.getChildren().addAll(panelTitle, this.buildingComboBox);
        this.setLeft(controlPanel);

        // Select the first building by default
        if (!this.buildingComboBox.getItems().isEmpty()) {
            this.buildingComboBox.getSelectionModel().selectFirst();
        }
    }
}
