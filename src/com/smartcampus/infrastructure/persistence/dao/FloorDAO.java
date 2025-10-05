package com.smartcampus.infrastructure.persistence.dao;

import com.smartcampus.data.model.Floor;
import java.util.ArrayList;
import java.util.List;

public class FloorDAO {

    public List<Floor> getFloorsByBuildingId(int buildingId) {
        List<Floor> floors = new ArrayList<>();
        // Sample data
        if (buildingId == 1) {
            floors.add(new Floor(1, 0, null)); // Ground floor
            floors.add(new Floor(2, 1, null)); // First floor
        } else if (buildingId == 2) {
            floors.add(new Floor(3, 0, null));
        } else if (buildingId == 3) {
            floors.add(new Floor(4, 0, null));
        }
        return floors;
    }
}
