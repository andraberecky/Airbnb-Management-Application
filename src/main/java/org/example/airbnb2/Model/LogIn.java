package org.example.airbnb2.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn {


    public boolean checkLoginCredentials(String firstName, String lastName) {

        String url = "jdbc:postgresql://localhost:5432/Airbnb";
        String user = "postgres";
        String password = "andrastefi04";

        //check if the person exists in the database.
        String query = "SELECT 1 FROM persons WHERE first_name = ? AND last_name = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); //if a result is found, the person is in the database

        } catch (SQLException e) {
            e.printStackTrace();
            return false; //LogIn has failed
        }
    }
}
