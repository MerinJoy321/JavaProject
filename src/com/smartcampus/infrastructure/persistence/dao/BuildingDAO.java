package com.smartcampus.infrastructure.persistence.dao;

import com.smartcampus.data.model.Building;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAO {

    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();
        // Sample data
        buildings.add(new Building(1, "Main Building", null));
        buildings.add(new Building(2, "Library", null));
        buildings.add(new Building(3, "Cafeteria", null));
        return buildings;
    }
}
