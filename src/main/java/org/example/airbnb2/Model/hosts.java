package org.example.airbnb2.Model;

public class hosts  extends persons {
    private static final String phoneNumber =null ;
    private String id;

    public hosts(String firstName, String lastName, String id) {
        super(firstName, lastName, phoneNumber);
        this.id = id;
    }

    public String getPropertyName() {
        return id;
    }

    @Override
    public String getFullName() {
        return id + " " + super.getFullName() ;
    }

    @Override
    public String getRole() {
        return "Host";
    }
}

