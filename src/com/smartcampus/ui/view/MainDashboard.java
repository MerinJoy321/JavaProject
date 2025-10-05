package com.smartcampus.ui.view;

import com.smartcampus.data.CampusDataService;
import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.User;
import com.smartcampus.data.model.MapViewOption;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainDashboard extends BorderPane {

    private final MapView mapView;
    private final List<MapViewOption> mapViewOptions;
    private final User currentUser;
    private final Runnable signOutCallback;

    // Navigation history
    private final Stack<MapViewOption> backHistory = new Stack<>();
    private final Stack<MapViewOption> forwardHistory = new Stack<>();
    private MapViewOption currentView;
    private Button backButton;
    private Button forwardButton;

    public MainDashboard(User user, Runnable signOutCallback) {
        this.currentUser = user;
        this.signOutCallback = signOutCallback;
        this.mapView = new MapView();

        // Prepare map view options for 2 blocks, 2 floors each, and overall campus layout
        this.mapViewOptions = new ArrayList<>();
        mapViewOptions.add(new MapViewOption("Campus Layout", "block layout.png"));
        mapViewOptions.add(new MapViewOption("Block A Ground Floor", "block-A ground floor.png"));
        mapViewOptions.add(new MapViewOption("Block A First Floor", "block-A first floor.png"));
        mapViewOptions.add(new MapViewOption("Block B Ground Floor", "block-B ground floor.png"));
        mapViewOptions.add(new MapViewOption("Block B First Floor", "block-B first floor.png"));

        // --- Top Header with Navigation and Sign Out ---
        setupTopHeader();

        // --- Center Map View ---
        this.setCenter(mapView);
        BorderPane.setMargin(mapView, new Insets(10));

        // --- Left Control Panel (Map View Selector) ---
        setupControlPanel();

        // Initial render
        currentView = mapViewOptions.get(0);
        mapView.renderImage(currentView.getImagePath());
    }

    private void setupControlPanel() {
        VBox controlPanel = new VBox(20);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setPrefWidth(250);
        controlPanel.setStyle("-fx-background-color: #e9ecef;");

        Label panelTitle = new Label("Campus Explorer");
        panelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Create a dropdown to select a map view option
        ComboBox<MapViewOption> mapViewComboBox = new ComboBox<>();
        mapViewComboBox.setItems(FXCollections.observableArrayList(mapViewOptions));
        mapViewComboBox.setMaxWidth(Double.MAX_VALUE);

        // When a map view option is selected, render the corresponding image
        mapViewComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldOption, newOption) -> {
            if (newOption != null && !newOption.equals(currentView)) {
                // Add current view to back history
                if (currentView != null) {
                    backHistory.push(currentView);
                }
                // Clear forward history when navigating to new view
                forwardHistory.clear();

                currentView = newOption;
                mapView.renderImage(newOption.getImagePath());
                updateNavigationButtons();
            }
        });

        controlPanel.getChildren().addAll(panelTitle, mapViewComboBox);
        this.setLeft(controlPanel);

        // Select the first option by default
        if (!mapViewComboBox.getItems().isEmpty()) {
            mapViewComboBox.getSelectionModel().selectFirst();
        }
    }

    private void setupTopHeader() {
        HBox headerBox = new HBox(20);
        headerBox.setPadding(new Insets(10));
        headerBox.setAlignment(Pos.CENTER_LEFT);
        headerBox.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 0 0 1 0;");

        // Navigation buttons
        backButton = new Button("← Back");
        backButton.setDisable(true);
        backButton.setOnAction(e -> goBack());

        forwardButton = new Button("Forward →");
        forwardButton.setDisable(true);
        forwardButton.setOnAction(e -> goForward());

        // Title
        Label title = new Label("Smart Campus Navigation");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        HBox.setHgrow(title, javafx.scene.layout.Priority.ALWAYS);
        title.setAlignment(Pos.CENTER);

        // Sign out button
        Button signOutButton = new Button("Sign Out");
        signOutButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        signOutButton.setOnAction(e -> signOutCallback.run());

        headerBox.getChildren().addAll(backButton, forwardButton, title, signOutButton);
        this.setTop(headerBox);
    }

    private void goBack() {
        if (!backHistory.isEmpty()) {
            forwardHistory.push(currentView);
            currentView = backHistory.pop();
            mapView.renderImage(currentView.getImagePath());
            updateNavigationButtons();
        }
    }

    private void goForward() {
        if (!forwardHistory.isEmpty()) {
            backHistory.push(currentView);
            currentView = forwardHistory.pop();
            mapView.renderImage(currentView.getImagePath());
            updateNavigationButtons();
        }
    }

    private void updateNavigationButtons() {
        if (backButton != null) {
            backButton.setDisable(backHistory.isEmpty());
        }
        if (forwardButton != null) {
            forwardButton.setDisable(forwardHistory.isEmpty());
        }
    }
}
