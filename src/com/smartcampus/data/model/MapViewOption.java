package com.smartcampus.data.model;

public class MapViewOption {
    private String displayName;
    private String imagePath;

    public MapViewOption(String displayName, String imagePath) {
        this.displayName = displayName;
        this.imagePath = imagePath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
