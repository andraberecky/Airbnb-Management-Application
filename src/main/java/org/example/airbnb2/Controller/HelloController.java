package org.example.airbnb2.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.airbnb2.Model.*;

public class HelloController {

    @FXML
    private ImageView imageView;


    public void initialize() {
        Image image = new Image("file:C:\\Users\\Andra\\Desktop\\OOP-project\\poza2.jpg");

        imageView.setImage(image);
    }

    @FXML
    private VBox personsListVBox;

    private final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "";

    @FXML
    protected void onHostsButtonClick() {
        populateList("SELECT id_person, first_name, last_name, type FROM persons WHERE type = 'host'");
    }
    @FXML
    protected void onGuestsButtonClick() {
        populateList("SELECT id_person, first_name, last_name, type FROM persons WHERE type = 'guest'");
    }
    private final Guest guestModel = new Guest();

    @FXML
    private void onAddGuestsButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Guest");
        dialog.setHeaderText("Enter guest details.");
        dialog.setContentText("Enter Guest ID, Last Name, First Name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String input = result.get();
            String[] details = input.split(",");

            if (details.length == 3) {
                try {
                    int id = Integer.parseInt(details[0].trim());
                    String lastName = details[1].trim();
                    String firstName = details[2].trim();

                    // Add guest using the Guest model
                    boolean success = guestModel.addGuest(id, firstName, lastName);

                    if (success) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Guest Added Successfully", "The new guest has been added to the database.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Failed to Add Guest", "There was an issue adding the guest.");
                    }

                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "Invalid ID Format", "Please ensure the ID is a valid integer.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Missing Data", "Please provide all three details: ID, Last Name, and First Name.");
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    // Action for "View All" button click
    @FXML
    protected void onViewAllClick() {
        populateList("SELECT id_person, first_name, last_name, type FROM persons");
    }

    private void populateList(String query) {
        personsListVBox.getChildren().clear();


        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {


            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER_LEFT);


            gridPane.add(new Label("ID"), 0, 0);
            gridPane.add(new Label("Type"), 1, 0);
            gridPane.add(new Label("Last Name"), 2, 0);
            gridPane.add(new Label("First Name"), 3, 0);


            for (int i = 0; i < 4; i++) {
                Label header = (Label) gridPane.getChildren().get(i);
                header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }

            personsListVBox.getChildren().add(gridPane);


            int rowIndex = 1;
            while (rs.next()) {
                int idPerson = rs.getInt("id_person");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String type = rs.getString("type");


                gridPane.add(new Label(String.valueOf(idPerson)), 0, rowIndex);
                gridPane.add(new Label(type), 1, rowIndex);
                gridPane.add(new Label(lastName), 2, rowIndex);
                gridPane.add(new Label(firstName), 3, rowIndex);


                rowIndex++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            personsListVBox.getChildren().add(new Label("Error fetching data."));
        }
    }

    private Reviews reviewsModel = new Reviews();

    @FXML
    public void onReviewsButtonClick() {
        personsListVBox.getChildren().clear();

        List<Reviews.Review> reviews = reviewsModel.loadReviews();


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        gridPane.add(new Label("Property ID"), 0, 0);
        gridPane.add(new Label("Guest ID"), 1, 0);
        gridPane.add(new Label("Cleaning"), 2, 0);
        gridPane.add(new Label("Communication"), 3, 0);
        gridPane.add(new Label("Location"), 4, 0);
        gridPane.add(new Label("Value for Money"), 5, 0);

        for (int i = 0; i < 6; i++) {
            Label header = (Label) gridPane.getChildren().get(i);
            header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        }

        personsListVBox.getChildren().add(gridPane);

        int rowIndex = 1;
        for (Reviews.Review review : reviews) {
            gridPane.add(new Label(String.valueOf(review.getIdProperty())), 0, rowIndex);
            gridPane.add(new Label(String.valueOf(review.getIdGuest())), 1, rowIndex);
            gridPane.add(new Label(String.valueOf(review.getCleaningRating())), 2, rowIndex);
            gridPane.add(new Label(String.valueOf(review.getCommunicationRating())), 3, rowIndex);
            gridPane.add(new Label(String.valueOf(review.getLocationRating())), 4, rowIndex);
            gridPane.add(new Label(String.valueOf(review.getValueForMoney())), 5, rowIndex);

            rowIndex++;
        }
    }
    private Properties propertiesModel = new Properties();

    @FXML
    public void onPropertiesButtonClick() {
        personsListVBox.getChildren().clear();

        List<Properties.Property> properties = propertiesModel.loadProperties();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        Label idPropertyHeader = new Label("ID Property");
        Label idHostHeader = new Label("ID Host");
        Label titleHeader = new Label("Title");
        Label propertyTypeHeader = new Label("Property Type");

        gridPane.add(idPropertyHeader, 0, 0);
        gridPane.add(idHostHeader, 1, 0);
        gridPane.add(titleHeader, 2, 0);
        gridPane.add(propertyTypeHeader, 3, 0);

        personsListVBox.getChildren().add(gridPane);

        int rowIndex = 1;
        for (Properties.Property property : properties) {
            Label idPropertyLabel = new Label(property.getIdProperty());
            Label idHostLabel = new Label(property.getIdHost());
            Label titleLabel = new Label(property.getTitle());
            Label propertyTypeLabel = new Label(property.getPropertyType());

            gridPane.add(idPropertyLabel, 0, rowIndex);
            gridPane.add(idHostLabel, 1, rowIndex);
            gridPane.add(titleLabel, 2, rowIndex);
            gridPane.add(propertyTypeLabel, 3, rowIndex);

            rowIndex++;
        }
    }

    private final Complaints complaintsModel = new Complaints();

    // Action for "View Complaints" button click
    @FXML
    protected void onComplaintsButtonClick() {
        personsListVBox.getChildren().clear();

        try {
            List<Complaints.Complaint> complaintsList = complaintsModel.getAllComplaints();

            if (complaintsList.isEmpty()) {
                personsListVBox.getChildren().add(new Label("No complaints found."));
                return;
            }
            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER_LEFT);

            gridPane.add(new Label("Complaint ID"), 0, 0);
            gridPane.add(new Label("Guest ID"), 1, 0);
            gridPane.add(new Label("Guest Name"), 2, 0);
            gridPane.add(new Label("Complaint"), 3, 0);

            for (int i = 0; i < 4; i++) {
                Label header = (Label) gridPane.getChildren().get(i);
                header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }

            personsListVBox.getChildren().add(gridPane);

            int rowIndex = 1;
            for (Complaints.Complaint complaint : complaintsList) {
                gridPane.add(new Label(String.valueOf(complaint.idComplaint)), 0, rowIndex);
                gridPane.add(new Label(String.valueOf(complaint.idGuest)), 1, rowIndex);
                gridPane.add(new Label(complaint.firstName + " " + complaint.lastName), 2, rowIndex);
                gridPane.add(new Label(complaint.complaint), 3, rowIndex);
                rowIndex++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            personsListVBox.getChildren().add(new Label("Error fetching complaints data."));
        }
    }

    private final Host hostModel = new Host();

    @FXML
    private void onAddHostButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Host");
        dialog.setHeaderText("Enter host details.");
        dialog.setContentText("Enter Host ID, First Name, Last Name:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String input = result.get();
            String[] details = input.split(",");

            if (details.length == 3) {
                try {
                    int id = Integer.parseInt(details[0].trim());
                    String lastName = details[1].trim();
                    String firstName = details[2].trim();

                    boolean success = hostModel.addHost(id, firstName, lastName);

                    if (success) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Host Added Successfully", "The new host has been added to the database.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Failed to Add Host", "There was an issue adding the host.");
                    }

                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Invalid Input", "Invalid ID Format", "Please ensure the ID is a valid integer.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Missing Data", "Please provide all three details: ID, First Name, and Last Name.");
            }
        }
    }


    private final Bookings bookingsHandler = new Bookings();

    @FXML
    protected void onBookingsButtonClick() {
        personsListVBox.getChildren().clear();

        Bookings bookingsHandler = new Bookings();
        try {
            List<Bookings.Booking> bookings = bookingsHandler.getAllBookings();

            if (bookings.isEmpty()) {
                personsListVBox.getChildren().add(new Label("No bookings found."));
                return;
            }

            GridPane gridPane = new GridPane();
            gridPane.setHgap(20);
            gridPane.setVgap(10);
            gridPane.setAlignment(Pos.CENTER_LEFT);

            // Add headers
            gridPane.add(new Label("Booking ID"), 0, 0);
            gridPane.add(new Label("Property ID"), 1, 0);
            gridPane.add(new Label("Guest ID"), 2, 0);
            gridPane.add(new Label("Booking Date"), 3, 0);
            gridPane.add(new Label("Total Price"), 4, 0);
            gridPane.add(new Label("Number of Guests"), 5, 0);
            gridPane.add(new Label("Check-In Date"), 6, 0);

            for (int i = 0; i < 7; i++) {
                Label header = (Label) gridPane.getChildren().get(i);
                header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            }

            int rowIndex = 1;
            for (Bookings.Booking booking : bookings) {
                gridPane.add(new Label(booking.idBooking), 0, rowIndex);
                gridPane.add(new Label(booking.idProperty), 1, rowIndex);
                gridPane.add(new Label(booking.idGuest), 2, rowIndex);
                gridPane.add(new Label(booking.bookingDate), 3, rowIndex);
                gridPane.add(new Label(booking.totalPrice), 4, rowIndex);
                gridPane.add(new Label(booking.nrGuest), 5, rowIndex);
                gridPane.add(new Label(booking.checkIn), 6, rowIndex);
                rowIndex++;
            }

            personsListVBox.getChildren().add(gridPane);

        } catch (SQLException e) {
            e.printStackTrace();
            personsListVBox.getChildren().add(new Label("Error fetching bookings."));
        }
    }


    //Search Button
    @FXML
    private TextField searchBar;
    private final Search searchHandler = new Search();

    @FXML
    protected void onSearchButtonClick() {
        String citySearch = searchBar.getText().trim();
        searchHandler.searchPropertiesByCity(citySearch, personsListVBox);
    }

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    private LogIn logInModel = new LogIn();

    @FXML
    private void onLoginButtonClick() {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if (logInModel.checkLoginCredentials(firstName, lastName)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Success", "You have logged in successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials, please try again.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    //Example of Guest and Hosts
    @FXML
    private Label fullNameLabel;

    private hosts host;
    private guests guest;

    @FXML
    protected void onHostsButtonClick2() {

        host = new hosts("John", "Doe", "777");
        fullNameLabel.setText(host.getFullName());
    }

    @FXML
    protected void onGuestsButtonClick2() {

        guest = new guests("Jane", "Doe", "778");
        fullNameLabel.setText(guest.getFullName());
    }





}
