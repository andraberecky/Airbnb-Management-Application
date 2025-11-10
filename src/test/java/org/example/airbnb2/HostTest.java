package org.example.airbnb2;

import org.example.airbnb2.Model.Host;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostTest {

    @Test
    void testAddHost() {
        Host host = new Host();

        // Test with valid input
        boolean result = host.addHost(77, "John", "Doe");
        assertTrue(result, "Host should be added successfully");

        // Test with invalid input (duplicate ID)
        result = host.addHost(77, "Jane", "Doe");
        assertFalse(result, "Host should not be added with a duplicate ID");
    }
}
