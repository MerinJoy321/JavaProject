package com.smartcampus.data.model;

import java.util.List;

public class Building {
    private final String name;
    private final List<Floor> floors;

    public Building(String name, List<Floor> floors) {
        this.name = name;
        this.floors = floors;
    }

    public String getName() { return name; }
    public List<Floor> getFloors() { return floors; }
}
