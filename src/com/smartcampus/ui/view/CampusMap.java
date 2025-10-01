package com.smartcampus.ui.view;

import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.Floor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CampusMap extends Pane {

    private Canvas canvas;
    private GraphicsContext gc;

    public CampusMap() {
        this.setPrefSize(800, 600);
        this.setStyle("-fx-background-color: #e8e8e8;");

        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        // Add a clip to ensure drawings stay within the border
        Rectangle clip = new Rectangle(800, 600);
        this.setClip(clip);

        this.getChildren().add(canvas);
    }

    public void renderCampus(Building building) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (building == null) {
            // Draw a placeholder message
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            gc.setFill(Color.GRAY);
            gc.fillText("Select a building to see the campus layout", 150, 280);
            return;
        }

        // Draw the selected building with a transparent fill
        drawBuilding(building);
    }

    private void drawBuilding(Building building) {
        // Simple representation of a building
        double buildingX = 50;
        double buildingY = 50;
        double buildingWidth = 700;
        double buildingHeight = 500;

        // Set a transparent fill
        gc.setFill(Color.rgb(100, 150, 200, 0.4)); // Light blue with transparency
        gc.setStroke(Color.DARKSLATEGRAY);
        gc.setLineWidth(3);

        gc.fillRect(buildingX, buildingY, buildingWidth, buildingHeight);
        gc.strokeRect(buildingX, buildingY, buildingWidth, buildingHeight);

        // Draw building name
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        gc.setFill(Color.BLACK);
        gc.fillText(building.getName(), buildingX + 20, buildingY + 40);

        // Render the first floor by default
        if (!building.getFloors().isEmpty()) {
            renderFloor(building.getFloors().get(0));
        }
    }

    public void renderFloor(Floor floor) {
        if (floor == null) {
            return;
        }

        // Draw a few sample rooms
        drawRoom("Room 101", 80, 80, 150, 100);
        drawRoom("Room 102", 250, 80, 150, 100);
        drawRoom("Room 103", 420, 80, 150, 100);
    }

    private void drawRoom(String name, double x, double y, double width, double height) {
        gc.setFill(Color.rgb(255, 255, 255, 0.6)); // White with transparency
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);

        gc.fillRect(x, y, width, height);
        gc.strokeRect(x, y, width, height);

        // Draw room label
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gc.setFill(Color.BLACK);
        gc.fillText(name, x + 10, y + 25);
    }
}