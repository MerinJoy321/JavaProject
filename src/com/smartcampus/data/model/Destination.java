package com.smartcampus.data.model;

public class Destination {
    private int id;
    private String code;
    private String name;
    private double x; // X-coordinate on the map pane
    private double y; // Y-coordinate on the map pane

    public Destination() {
    }

    public Destination(int id, String code, String name, double x, double y) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Destination(String code, String name, double x, double y) {
        this.code = code;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}
