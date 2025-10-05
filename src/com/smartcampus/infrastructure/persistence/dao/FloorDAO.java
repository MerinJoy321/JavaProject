package com.smartcampus.infrastructure.persistence.dao;

import com.smartcampus.data.model.Destination;
import com.smartcampus.data.model.Floor;
import com.smartcampus.infrastructure.persistence.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FloorDAO {

    public List<Floor> getFloorsByBuildingId(int buildingId) {
        List<Floor> floors = new ArrayList<>();
        String sql = "SELECT f.id, f.floor_number, b.name as building_name FROM floors f JOIN buildings b ON f.building_id = b.id WHERE f.building_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, buildingId);
            ResultSet rs = pstmt.executeQuery();

            DestinationDAO destinationDAO = new DestinationDAO();

            while (rs.next()) {
                int id = rs.getInt("id");
                int floorNumber = rs.getInt("floor_number");
                String buildingName = rs.getString("building_name");
                List<Destination> destinations = destinationDAO.getDestinationsByFloorId(id);
                Floor floor = new Floor(id, floorNumber, destinations);

                // Set image path based on building name and floor number
                String imagePath = getImagePath(buildingName, floorNumber);
                floor.setImagePath(imagePath);

                floors.add(floor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return floors;
    }

    private String getImagePath(String buildingName, int floorNumber) {
        String block = "";
        if (buildingName.contains("A")) {
            block = "A";
        } else if (buildingName.contains("B")) {
            block = "B";
        } else {
            return null; // No image for other blocks
        }

        String floorName = (floorNumber == 0) ? "ground floor" : "first floor";
        return "block-" + block + " " + floorName + ".png";
    }

    public void saveFloor(Floor floor, int buildingId) {
        String sql = "INSERT INTO floors (building_id, floor_number) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, buildingId);
            pstmt.setInt(2, floor.getFloorNumber());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                floor.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
