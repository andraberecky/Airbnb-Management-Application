package org.example.airbnb2;

import org.example.airbnb2.Model.LogIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogInTest {

    private LogIn logIn;

    @BeforeEach
    public void setUp() {
        logIn = new LogIn();
    }

    @Test
    public void testValidCredentials() {

        boolean result = logIn.checkLoginCredentials("Emma", "Jones");
        assertTrue(result, "Login should succeed for valid credentials.");
    }

    @Test
    public void testInvalidCredentials() {
        boolean result = logIn.checkLoginCredentials("Invalid", "User");
        assertFalse(result, "Login should fail for invalid credentials.");
    }

    @Test
    public void testEmptyCredentials() {
        boolean result = logIn.checkLoginCredentials("", "");
        assertFalse(result, "Login should fail for empty credentials.");
    }
}
