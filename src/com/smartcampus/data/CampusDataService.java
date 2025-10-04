package com.smartcampus.data;

import com.smartcampus.data.model.Building;
import com.smartcampus.infrastructure.persistence.dao.BuildingDAO;
import com.smartcampus.infrastructure.persistence.dao.FloorDAO;

import java.util.List;

/**
 * Service to provide campus data from the database.
 */
public class CampusDataService {

    private static BuildingDAO buildingDAO = new BuildingDAO();
    private static FloorDAO floorDAO = new FloorDAO();

    public static List<Building> getCampusBuildings() {
        List<Building> buildings = buildingDAO.getAllBuildings();
        // Load floors for each building
        for (Building building : buildings) {
            building.setFloors(floorDAO.getFloorsByBuildingId(building.getId()));
        }
        return buildings;
    }
}
