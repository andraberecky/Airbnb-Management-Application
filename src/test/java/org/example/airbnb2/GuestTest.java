package org.example.airbnb2;

import org.example.airbnb2.Model.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void testAddGuest() {
        Guest guest = new Guest();

        // Test with valid input
        boolean result = guest.addGuest(49, "John", "Doe");
        assertTrue(result, "Guest should be added successfully");

        // Test with invalid input - duplicate ID
        result = guest.addGuest(49, "Jane", "Doe");
        assertFalse(result, "Guest should not be added with a duplicate ID");
    }
}
