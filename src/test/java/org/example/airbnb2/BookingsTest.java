package org.example.airbnb2;

import org.example.airbnb2.Model.Bookings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingsTest {

    private Bookings bookings;

    @BeforeEach
    public void setUp() {
        bookings = new Bookings();
    }

    @Test
    public void testGetAllBookings_ReturnsResults() {
        try {
            List<Bookings.Booking> bookingList = bookings.getAllBookings();

            assertNotNull(bookingList, "Booking list should not be null.");

            assertTrue(bookingList.size() > 0, "Booking list should contain at least one record.");

            Bookings.Booking firstBooking = bookingList.get(0);
            assertNotNull(firstBooking.idBooking, "Booking ID should not be null.");
            assertNotNull(firstBooking.idProperty, "Property ID should not be null.");
            assertNotNull(firstBooking.idGuest, "Guest ID should not be null.");
            assertNotNull(firstBooking.bookingDate, "Booking Date should not be null.");
            assertNotNull(firstBooking.totalPrice, "Total Price should not be null.");
            assertNotNull(firstBooking.nrGuest, "Number of Guests should not be null.");
            assertNotNull(firstBooking.checkIn, "Check-In Date should not be null.");

        } catch (SQLException e) {
            fail("SQL Exception thrown: " + e.getMessage());
        }
    }


}
