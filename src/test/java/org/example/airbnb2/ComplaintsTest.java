package org.example.airbnb2;

import org.example.airbnb2.Model.Complaints;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComplaintsTest {

    @Test
    public void testGetAllComplaints() {
        Complaints complaintsModel = new Complaints();

        try {
            List<Complaints.Complaint> complaintsList = complaintsModel.getAllComplaints();

            assertNotNull(complaintsList, "The complaints list should not be null.");

            assertTrue(complaintsList.size() > 0, "The complaints list should contain at least one complaint.");

            Complaints.Complaint firstComplaint = complaintsList.get(0);
            assertNotNull(firstComplaint.idComplaint, "Complaint ID should not be null.");
            assertNotNull(firstComplaint.idGuest, "Guest ID should not be null.");
            assertNotNull(firstComplaint.firstName, "First Name should not be null.");
            assertNotNull(firstComplaint.lastName, "Last Name should not be null.");
            assertNotNull(firstComplaint.complaint, "Complaint should not be null.");

            System.out.println("First Complaint Data: ");
            System.out.println("Complaint ID: " + firstComplaint.idComplaint);
            System.out.println("Guest ID: " + firstComplaint.idGuest);
            System.out.println("Name: " + firstComplaint.firstName + " " + firstComplaint.lastName);
            System.out.println("Complaint: " + firstComplaint.complaint);

        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }
}
