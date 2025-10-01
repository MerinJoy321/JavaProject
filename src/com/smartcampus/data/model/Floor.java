package com.smartcampus.data.model;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Destination> destinations;

    public Floor(int floorNumber, List<Destination> destinations) {
        this.floorNumber = floorNumber;
        this.destinations = destinations;
    }

    public int getFloorNumber() { return floorNumber; }
    public List<Destination> getDestinations() { return destinations; }
    
    @Override
    public String toString() {
        return "Floor " + floorNumber;
    }
}
