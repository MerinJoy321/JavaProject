package com.smartcampus.data.model;

import java.util.List;

public class Floor {
    private int id;
    private int floorNumber;
    private List<Destination> destinations;
    private String imagePath; // Path to floor layout image

    public Floor() {
    }

    public Floor(int id, int floorNumber, List<Destination> destinations) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.destinations = destinations;
        // Set image path based on floor number and building (mock logic)
        if (floorNumber == 0) {
            this.imagePath = "freepik__campus-layout-block-a-ground-floor-clean-topdown-a__9017.png";
        } else if (floorNumber == 1) {
            this.imagePath = "freepik__campus-layout-block-a-first-floor-topdown-floor-pl__9014.png";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public List<Destination> getDestinations() { return destinations; }
    public void setDestinations(List<Destination> destinations) { this.destinations = destinations; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public String toString() {
        if (floorNumber == 0) {
            return "Ground Floor";
        } else if (floorNumber == 1) {
            return "First Floor";
        } else {
            return "Floor " + floorNumber;
        }
    }
}
