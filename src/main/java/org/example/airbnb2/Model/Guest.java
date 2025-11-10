package org.example.airbnb2.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Guest {

    // Method to add a guest to the database
    public boolean addGuest(int id, String firstName, String lastName) {
        String url = "jdbc:postgresql://localhost:5432/Airbnb";
        String user = "postgres";
        String password = "andrastefi04";

        String insertQuery = "INSERT INTO persons (id_person, first_name, last_name, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, "guest");

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
