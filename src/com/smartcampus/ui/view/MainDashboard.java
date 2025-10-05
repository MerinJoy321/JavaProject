package com.smartcampus.ui.view;

import com.smartcampus.data.CampusDataService;
import com.smartcampus.data.model.Building;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class MainDashboard extends BorderPane {

    private final MapView mapView;
    private final List<Building> buildings;

    public MainDashboard() {
        this.buildings = CampusDataService.getCampusBuildings();
        this.mapView = new MapView();

        // --- Top Header ---
        Label title = new Label("Smart Campus Navigation");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setPadding(new Insets(10));
        this.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

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

        // Create a dropdown to select a building
        ComboBox<Building> buildingComboBox = new ComboBox<>();
        buildingComboBox.setItems(FXCollections.observableArrayList(buildings));
        buildingComboBox.setMaxWidth(Double.MAX_VALUE);

        // When a building is selected, render it on the map
        buildingComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldBuilding, newBuilding) -> {
            if (newBuilding != null) {
                mapView.renderBuilding(newBuilding);
            }
        });

        controlPanel.getChildren().addAll(panelTitle, buildingComboBox);
        this.setLeft(controlPanel);

        // Select the first building by default
        if (!buildingComboBox.getItems().isEmpty()) {
            buildingComboBox.getSelectionModel().selectFirst();
        }
    }
}
