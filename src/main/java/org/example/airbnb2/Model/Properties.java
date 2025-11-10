package org.example.airbnb2.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Properties {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "andrastefi04";

    public static class Property {
        private String idProperty;
        private String idHost;
        private String title;
        private String propertyType;

        public Property(String idProperty, String idHost, String title, String propertyType) {
            this.idProperty = idProperty;
            this.idHost = idHost;
            this.title = title;
            this.propertyType = propertyType;
        }

        public String getIdProperty() {
            return idProperty;
        }

        public String getIdHost() {
            return idHost;
        }

        public String getTitle() {
            return title;
        }

        public String getPropertyType() {
            return propertyType;
        }
    }

    public List<Property> loadProperties() {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT id_property, id_host, title, property_type FROM properties";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Property property = new Property(
                        rs.getString("id_property"),
                        rs.getString("id_host"),
                        rs.getString("title"),
                        rs.getString("property_type")
                );
                properties.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
