package org.example.airbnb2.Model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.*;

public class Search {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "andrastefi04";

    public void searchPropertiesByCity(String citySearch, VBox personsListVBox) {

        personsListVBox.getChildren().clear();

        String query = "SELECT id_property, id_host, title, property_type, city FROM properties " +
                "WHERE city ILIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + citySearch + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                GridPane gridPane = new GridPane();
                gridPane.setHgap(20);
                gridPane.setVgap(10);
                gridPane.setAlignment(Pos.TOP_CENTER);

                Label[] headers = {
                        new Label("ID Property"), new Label("ID Host"),
                        new Label("Title"), new Label("Property Type"), new Label("City")
                };

                for (int i = 0; i < headers.length; i++) {
                    headers[i].setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                    gridPane.add(headers[i], i, 0);
                }

                personsListVBox.getChildren().add(gridPane);

                int rowIndex = 1;
                while (rs.next()) {
                    Label idPropertyLabel = new Label(rs.getString("id_property"));
                    Label idHostLabel = new Label(rs.getString("id_host"));
                    Label titleLabel = new Label(rs.getString("title"));
                    Label propertyTypeLabel = new Label(rs.getString("property_type"));
                    Label cityLabel = new Label(rs.getString("city"));

                    gridPane.add(idPropertyLabel, 0, rowIndex);
                    gridPane.add(idHostLabel, 1, rowIndex);
                    gridPane.add(titleLabel, 2, rowIndex);
                    gridPane.add(propertyTypeLabel, 3, rowIndex);
                    gridPane.add(cityLabel, 4, rowIndex);

                    rowIndex++;
                }

                if (rowIndex == 1) {
                    personsListVBox.getChildren().add(new Label("No results found for the city: " + citySearch));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            personsListVBox.getChildren().add(new Label("Error fetching search results."));
        }
    }
}