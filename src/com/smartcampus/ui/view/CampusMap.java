package com.smartcampus.ui.view;


import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.Floor;
import com.smartcampus.data.model.Destination;
import com.smartcampus.core.Pathfinding;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Platform;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CampusMap extends ScrollPane {

    private Group group;
    private Canvas canvas;
    private GraphicsContext gc;
    private Scale scale;
    private Rotate rotate;
    private double scaleValue = 1.0;
    private double heading = 0.0; // User heading in degrees
    private double lastMouseX, lastMouseY;
    private Building currentBuilding;
    private Floor currentFloor;
    private double userX = 400, userY = 300; // Initial user position
    private Timeline userMovementTimeline;
    private List<double[]> navigationPath; // Current navigation path
    private Map<String, Image> floorImages; // Cache for floor images

    public CampusMap() {
        this.setPrefSize(800, 600);
        this.setStyle("-fx-background-color: #e8e8e8;");

        group = new Group();
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        scale = new Scale(scaleValue, scaleValue);
        rotate = new Rotate(heading, canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.getTransforms().addAll(scale, rotate);

        floorImages = new HashMap<>();

        group.getChildren().add(canvas);
        this.setContent(group);

        // Mouse events for panning
        this.setOnMousePressed(e -> {
            lastMouseX = e.getX();
            lastMouseY = e.getY();
        });

        this.setOnMouseDragged(e -> {
            double deltaX = e.getX() - lastMouseX;
            double deltaY = e.getY() - lastMouseY;
            this.setHvalue(this.getHvalue() - deltaX / this.getWidth());
            this.setVvalue(this.getVvalue() - deltaY / this.getHeight());
            lastMouseX = e.getX();
            lastMouseY = e.getY();
        });

        // Zoom with mouse wheel
        this.setOnScroll(e -> {
            double zoomFactor = 1.1;
            if (e.getDeltaY() < 0) {
                zoomFactor = 1 / zoomFactor;
            }
            scaleValue *= zoomFactor;
            scale.setX(scaleValue);
            scale.setY(scaleValue);

            // Adjust scroll to zoom towards mouse
            double mouseX = e.getX();
            double mouseY = e.getY();
            double newH = (this.getHvalue() * this.getWidth() + mouseX) * zoomFactor - mouseX;
            double newV = (this.getVvalue() * this.getHeight() + mouseY) * zoomFactor - mouseY;
            this.setHvalue(newH / this.getWidth());
            this.setVvalue(newV / this.getHeight());
        });

        // Keyboard navigation for floors
        this.setOnKeyPressed(e -> {
            if (currentBuilding == null) return;
            int floorIndex = currentBuilding.getFloors().indexOf(currentFloor);
            switch (e.getCode()) {
                case UP:
                case RIGHT:
                    if (floorIndex < currentBuilding.getFloors().size() - 1) {
                        currentFloor = currentBuilding.getFloors().get(floorIndex + 1);
                        renderFloor(currentFloor);
                    }
                    break;
                case DOWN:
                case LEFT:
                    if (floorIndex > 0) {
                        currentFloor = currentBuilding.getFloors().get(floorIndex - 1);
                        renderFloor(currentFloor);
                    }
                    break;
                default:
                    break;
            }
        });

        // Request focus to receive key events
        this.setFocusTraversable(true);
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

        currentBuilding = building;
        currentFloor = building.getFloors().isEmpty() ? null : building.getFloors().get(0);

        // Draw the selected building with a transparent fill
        drawBuilding(building);

        // Draw user avatar
        drawUser();

        // Draw current floor if selected
        if (currentFloor != null) {
            renderFloor(currentFloor);
        }
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
    }

    public void renderFloor(Floor floor) {
        if (floor == null) {
            return;
        }

        currentFloor = floor;

        // Clear previous floor overlay
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw floor image if available
        if (floor.getImagePath() != null && !floor.getImagePath().isEmpty()) {
            Image img = floorImages.computeIfAbsent(floor.getImagePath(), path -> new Image("file:" + path));
            if (img != null && !img.isError()) {
                gc.setGlobalAlpha(0.8); // Semi-transparent overlay
                gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setGlobalAlpha(1.0);
            }
        } else {
            // Fallback to drawing building base
            if (currentBuilding != null) {
                drawBuilding(currentBuilding);
            }
        }

        // Draw floor overlay (rooms/destinations) on top of image
        for (var dest : floor.getDestinations()) {
            drawRoom(dest.getName(), dest.getX(), dest.getY(), 150, 100);
        }

        // Draw navigation path if exists
        drawNavigationPath();

        // Draw user avatar on top
        drawUser();
    }

    private void drawRoom(String name, double x, double y, double width, double height) {
        if ("Stairs".equals(name)) {
            // Draw stairs as a special element
            gc.setFill(Color.rgb(169, 169, 169, 0.8)); // Dark gray for stairs
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);

            // Draw stair steps
            for (int i = 0; i < 5; i++) {
                gc.strokeLine(x + i * 10, y + height - i * 10, x + width - (4 - i) * 10, y + height - i * 10);
            }
            gc.fillRect(x, y, width, height);
            gc.strokeRect(x, y, width, height);
        } else {
            gc.setFill(Color.rgb(255, 255, 255, 0.6)); // White with transparency
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(2);

            gc.fillRect(x, y, width, height);
            gc.strokeRect(x, y, width, height);
        }

        // Draw room label
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        gc.setFill(Color.BLACK);
        gc.fillText(name, x + 10, y + 25);
    }

    private void drawUser() {
        double radius = 10;
        gc.setFill(Color.RED);
        gc.fillOval(userX - radius, userY - radius, radius * 2, radius * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(userX - radius, userY - radius, radius * 2, radius * 2);
    }

    private void drawNavigationPath() {
        if (navigationPath == null || navigationPath.size() < 2) return;

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);
        for (int i = 0; i < navigationPath.size() - 1; i++) {
            double[] p1 = navigationPath.get(i);
            double[] p2 = navigationPath.get(i + 1);
            gc.strokeLine(p1[0], p1[1], p2[0], p2[1]);
        }
    }

    public void startUserMovementAnimation() {
        if (userMovementTimeline != null) {
            userMovementTimeline.stop();
        }

        userMovementTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            // Simulate user movement: move right and down
            userX += 2;
            userY += 1;

            // Keep user within canvas bounds
            userX = Math.min(userX, canvas.getWidth() - 10);
            userY = Math.min(userY, canvas.getHeight() - 10);

            // Redraw floor and user
            Platform.runLater(() -> {
                if (currentFloor != null) {
                    renderFloor(currentFloor);
                } else if (currentBuilding != null) {
                    renderCampus(currentBuilding);
                }
            });

            // Auto-scroll to follow user
            double hValue = (userX * scaleValue - this.getViewportBounds().getWidth() / 2) / (canvas.getWidth() * scaleValue - this.getViewportBounds().getWidth());
            double vValue = (userY * scaleValue - this.getViewportBounds().getHeight() / 2) / (canvas.getHeight() * scaleValue - this.getViewportBounds().getHeight());
            this.setHvalue(clamp(hValue, 0, 1));
            this.setVvalue(clamp(vValue, 0, 1));
        }));
        userMovementTimeline.setCycleCount(Timeline.INDEFINITE);
        userMovementTimeline.play();
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public void startNavigation(Destination destination) {
        if (currentFloor == null || destination == null) return;

        // Create a mock start destination at user position
        Destination start = new Destination("Start", "Start", userX, userY);

        navigationPath = Pathfinding.findPath(start, destination);

        // Update heading towards first path point
        if (navigationPath.size() > 1) {
            double[] next = navigationPath.get(1);
            heading = Math.toDegrees(Math.atan2(next[1] - userY, next[0] - userX));
            rotate.setAngle(heading);
        }
    }

    public void updateHeading(double deltaHeading) {
        heading += deltaHeading;
        rotate.setAngle(heading);
        renderFloor(currentFloor); // Redraw to apply rotation
    }

    public void stopNavigation() {
        navigationPath = null;
        renderFloor(currentFloor);
    }

    public void renderImage(String imagePath) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (imagePath != null && !imagePath.isEmpty()) {
            Image img = floorImages.computeIfAbsent(imagePath, path -> new Image("file:" + path));
            if (img != null && !img.isError()) {
                gc.setGlobalAlpha(0.9); // Semi-transparent overlay
                gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setGlobalAlpha(1.0);
            }
        }

        // Draw user avatar on top
        drawUser();
    }
}
