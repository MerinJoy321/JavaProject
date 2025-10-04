package com.smartcampus.data.model;

import java.util.List;

public class Floor {
    private int id;
    private int floorNumber;
    private List<Destination> destinations;

    public Floor() {
    }

    public Floor(int id, int floorNumber, List<Destination> destinations) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.destinations = destinations;
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
