package org.example.airbnb2;

import org.example.airbnb2.Model.Reviews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewsTest {

    private Reviews reviews;

    @BeforeEach
    void setUp() {
        reviews = new Reviews();
    }


    @Test
    void testLoadReviews_noReviews() throws SQLException {

        clearReviews();

        List<Reviews.Review> reviewList = reviews.loadReviews();

        assertNotNull(reviewList);
        assertTrue(reviewList.isEmpty());
    }

    private void insertTestReview(int idProperty, int idGuest, int cleaningRating, int communicationRating,
                                  int locationRating, int valueForMoney) {
        String query = "INSERT INTO reviews (id_property, id_guest, cleaning_rating, communication_rating, " +
                "location_rating, value_for_money) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Airbnb", "postgres", "andrastefi04");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idProperty);
            pstmt.setInt(2, idGuest);
            pstmt.setInt(3, cleaningRating);
            pstmt.setInt(4, communicationRating);
            pstmt.setInt(5, locationRating);
            pstmt.setInt(6, valueForMoney);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearReviews() {
        String query = "DELETE FROM reviews";
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Airbnb", "postgres", "andrastefi04");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
