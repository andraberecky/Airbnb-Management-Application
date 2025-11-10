package org.example.airbnb2.Model;

public class guests extends persons {
    private static final String phoneNumber = null;
    private String id;

    public guests(String firstName, String lastName, String id) {
        super(firstName, lastName, phoneNumber);
        this.id = id;
    }

    public String getReservationDate() {
        return id;
    }

    @Override
    public String getFullName() {
        return id +" " + super.getFullName();
    }

    @Override
    public String getRole() {
        return "Guest";
    }
}
