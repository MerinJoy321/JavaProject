package com.smartcampus.infrastructure.persistence.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            // Execute schema.sql
            executeSqlFile(stmt, "src/com/smartcampus/infrastructure/persistence/schema.sql");

            // Execute data.sql
            executeSqlFile(stmt, "src/com/smartcampus/infrastructure/persistence/data.sql");

            System.out.println("Database initialized successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeSqlFile(Statement stmt, String filePath) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            // Split by semicolon and execute each statement
            String[] statements = sql.toString().split(";");
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement.trim());
                }
            }
        }
    }
}
