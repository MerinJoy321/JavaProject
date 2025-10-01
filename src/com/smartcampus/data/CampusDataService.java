package com.smartcampus.data;

import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.Destination;
import com.smartcampus.data.model.Floor;

import java.util.Arrays;
import java.util.List;

/**
 * A mock service to provide campus data.
 * In a real application, this would fetch data from a database.
 */
public class CampusDataService {

    public static List<Building> getCampusBuildings() {
        return Arrays.asList(
            createBuilding("A Block (Engineering)", 3),
            createBuilding("B Block (Science)", 3),
            createBuilding("C Block (Arts)", 3),
            createBuilding("D Block (Library & Admin)", 3)
        );
    }

    private static Building createBuilding(String name, int numFloors) {
        Floor[] floors = new Floor[numFloors];
        for (int i = 0; i < numFloors; i++) {
            floors[i] = createFloor(i, name.charAt(0));
        }
        return new Building(name, Arrays.asList(floors));
    }

    private static Floor createFloor(int floorNumber, char blockPrefix) {
        // Create 4 destinations per floor with some layout variation
        List<Destination> destinations = Arrays.asList(
            new Destination(blockPrefix + "" + floorNumber + "01", "Room " + floorNumber + "01", 100, 100 + (floorNumber * 10)),
            new Destination(blockPrefix + "" + floorNumber + "02", "Lab " + floorNumber + "02", 400, 100 - (floorNumber * 10)),
            new Destination(blockPrefix + "" + floorNumber + "03", "Room " + floorNumber + "03", 100, 350 - (floorNumber * 10)),
            new Destination(blockPrefix + "" + floorNumber + "04", "Office " + floorNumber + "04", 400, 350 + (floorNumber * 10))
        );
        return new Floor(floorNumber, destinations);
    }
}
