package com.smartcampus.data.model;

public class Destination {
    private final String id;
    private final String name;
    private final double x; // X-coordinate on the map pane
    private final double y; // Y-coordinate on the map pane

    public Destination(String id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
}
