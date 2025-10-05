package com.smartcampus.ui.view;

import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.Floor;
import javafx.scene.layout.StackPane;

public class MapView extends StackPane {

    private CampusMap campusMap;

    public MapView() {
        this.setStyle("-fx-background-color: #f0f4f8; -fx-border-color: #c0c0c0; -fx-border-width: 1;");
        this.setPrefSize(800, 600);

        campusMap = new CampusMap();
        this.getChildren().add(campusMap);
    }

    public void renderBuilding(Building building) {
        campusMap.renderCampus(building);
    }

    public void renderFloor(Floor floor) {
        // The CampusMap now handles floor rendering internally
        // This method can be adapted if direct floor control is needed later
        if (floor != null) {
            campusMap.renderFloor(floor);
        }
    }

    public void renderImage(String imagePath) {
        campusMap.renderImage(imagePath);
    }
}
