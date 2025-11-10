package org.example.airbnb2.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bookings {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "andrastefi04";

    public static class Booking {
        public String idBooking;
        public String idProperty;
        public String idGuest;
        public String bookingDate;
        public String totalPrice;
        public String nrGuest;
        public String checkIn;

        public Booking(String idBooking, String idProperty, String idGuest, String bookingDate, String totalPrice, String nrGuest, String checkIn) {
            this.idBooking = idBooking;
            this.idProperty = idProperty;
            this.idGuest = idGuest;
            this.bookingDate = bookingDate;
            this.totalPrice = totalPrice;
            this.nrGuest = nrGuest;
            this.checkIn = checkIn;
        }
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        String query = "SELECT id_booking, id_property, id_guest, booking_date, total_price, nr_guest, check_in FROM bookings";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getString("id_booking"),
                        rs.getString("id_property"),
                        rs.getString("id_guest"),
                        rs.getString("booking_date"),
                        rs.getString("total_price"),
                        rs.getString("nr_guest"),
                        rs.getString("check_in")
                ));
            }
        }

        return bookings;
    }
}
