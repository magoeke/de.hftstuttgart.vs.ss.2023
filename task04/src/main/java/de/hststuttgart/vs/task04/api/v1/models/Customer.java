package de.hststuttgart.vs.task04.api.v1.models;

public class Customer {

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public Customer firstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Customer lastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

}
