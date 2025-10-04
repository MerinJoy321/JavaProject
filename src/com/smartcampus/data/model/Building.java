package com.smartcampus.data.model;

import java.util.List;

public class Building {
    private int id;
    private String name;
    private List<Floor> floors;

    public Building() {
    }

    public Building(int id, String name, List<Floor> floors) {
        this.id = id;
        this.name = name;
        this.floors = floors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Floor> getFloors() { return floors; }
    public void setFloors(List<Floor> floors) { this.floors = floors; }
}
