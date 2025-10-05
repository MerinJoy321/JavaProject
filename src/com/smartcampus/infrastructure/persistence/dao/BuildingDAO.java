package com.smartcampus.infrastructure.persistence.dao;

import com.smartcampus.data.model.Building;
import com.smartcampus.data.model.Floor;
import com.smartcampus.infrastructure.persistence.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAO {

    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();
        String sql = "SELECT id, name FROM buildings";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Building building = new Building(id, name, null); // floors will be loaded separately
                buildings.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buildings;
    }

    public Building getBuildingById(int id) {
        String sql = "SELECT id, name FROM buildings WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                // Load floors
                FloorDAO floorDAO = new FloorDAO();
                List<Floor> floors = floorDAO.getFloorsByBuildingId(id);
                return new Building(id, name, floors);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveBuilding(Building building) {
        String sql = "INSERT INTO buildings (name) VALUES (?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, building.getName());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                building.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
