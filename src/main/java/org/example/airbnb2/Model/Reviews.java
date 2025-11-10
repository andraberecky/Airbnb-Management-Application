package org.example.airbnb2.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reviews {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "andrastefi04";

    public static class Review {
        private int idProperty;
        private int idGuest;
        private int cleaningRating;
        private int communicationRating;
        private int locationRating;
        private int valueForMoney;

        public Review(int idProperty, int idGuest, int cleaningRating, int communicationRating,
                      int locationRating, int valueForMoney) {
            this.idProperty = idProperty;
            this.idGuest = idGuest;
            this.cleaningRating = cleaningRating;
            this.communicationRating = communicationRating;
            this.locationRating = locationRating;
            this.valueForMoney = valueForMoney;
        }

        public int getIdProperty() {
            return idProperty;
        }

        public int getIdGuest() {
            return idGuest;
        }

        public int getCleaningRating() {
            return cleaningRating;
        }

        public int getCommunicationRating() {
            return communicationRating;
        }

        public int getLocationRating() {
            return locationRating;
        }

        public int getValueForMoney() {
            return valueForMoney;
        }
    }

    public List<Review> loadReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT id_property, id_guest, cleaning_rating, communication_rating, location_rating, value_for_money FROM reviews";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("id_property"),
                        rs.getInt("id_guest"),
                        rs.getInt("cleaning_rating"),
                        rs.getInt("communication_rating"),
                        rs.getInt("location_rating"),
                        rs.getInt("value_for_money")
                );
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
