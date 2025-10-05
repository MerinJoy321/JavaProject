package com.smartcampus;

import com.smartcampus.data.CampusDataService;
import com.smartcampus.data.model.Building;
import com.smartcampus.infrastructure.persistence.config.DatabaseInitializer;

import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {
        System.out.println("Initializing database...");
        DatabaseInitializer.initializeDatabase();

        System.out.println("Testing data loading...");
        List<Building> buildings = CampusDataService.getCampusBuildings();

        System.out.println("Loaded " + buildings.size() + " buildings:");
        for (Building building : buildings) {
            System.out.println("Building: " + building.getName() + " (ID: " + building.getId() + ")");
            System.out.println("  Floors: " + building.getFloors().size());
            building.getFloors().forEach(floor -> {
                System.out.println("    Floor " + floor.getFloorNumber() + " (ID: " + floor.getId() + ") - Destinations: " + floor.getDestinations().size());
                floor.getDestinations().forEach(dest -> {
                    System.out.println("      " + dest.getName() + " at (" + dest.getX() + ", " + dest.getY() + ")");
                });
            });
        }

        System.out.println("Database connectivity test completed successfully!");
    }
}
