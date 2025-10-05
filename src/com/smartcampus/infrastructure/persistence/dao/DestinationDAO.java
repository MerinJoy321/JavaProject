package com.smartcampus.infrastructure.persistence.dao;

import com.smartcampus.data.model.Destination;
import com.smartcampus.infrastructure.persistence.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDAO {

    public List<Destination> getDestinationsByFloorId(int floorId) {
        List<Destination> destinations = new ArrayList<>();
        String sql = "SELECT id, code, name, x, y FROM destinations WHERE floor_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, floorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String name = rs.getString("name");
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                Destination destination = new Destination(id, code, name, x, y);
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public void saveDestination(Destination destination, int floorId) {
        String sql = "INSERT INTO destinations (floor_id, code, name, x, y) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, floorId);
            pstmt.setString(2, destination.getCode());
            pstmt.setString(3, destination.getName());
            pstmt.setDouble(4, destination.getX());
            pstmt.setDouble(5, destination.getY());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                destination.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
