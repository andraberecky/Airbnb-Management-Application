package org.example.airbnb2.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Complaints {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airbnb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "andrastefi04";


    public static class Complaint {
        public int idComplaint;
        public int idGuest;
        public String firstName;
        public String lastName;
        public String complaint;

        public Complaint(int idComplaint, int idGuest, String firstName, String lastName, String complaint) {
            this.idComplaint = idComplaint;
            this.idGuest = idGuest;
            this.firstName = firstName;
            this.lastName = lastName;
            this.complaint = complaint;
        }
    }

    public List<Complaint> getAllComplaints() throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT c.id_complaint, c.id_guest, p.first_name, p.last_name, c.complaint " +
                "FROM complaints c " +
                "JOIN persons p ON c.id_guest = p.id_person";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                complaints.add(new Complaint(
                        rs.getInt("id_complaint"),
                        rs.getInt("id_guest"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("complaint")
                ));
            }
        }

        return complaints;
    }
}
