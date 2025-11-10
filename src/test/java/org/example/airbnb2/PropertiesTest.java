package org.example.airbnb2.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PropertiesTest {

    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
    }

    @Test
    public void testLoadProperties() {

        List<Properties.Property> propertiesList = properties.loadProperties();

        assertFalse(propertiesList.isEmpty(), "Properties list should not be empty.");

        Properties.Property firstProperty = propertiesList.get(0);
        assertNotNull(firstProperty.getIdProperty(), "Property ID should not be null.");
        assertNotNull(firstProperty.getIdHost(), "Host ID should not be null.");
        assertNotNull(firstProperty.getTitle(), "Property title should not be null.");
        assertNotNull(firstProperty.getPropertyType(), "Property type should not be null.");
    }
}
