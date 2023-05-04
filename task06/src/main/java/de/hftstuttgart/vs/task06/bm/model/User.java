package de.hftstuttgart.vs.task06.bm.model;

import java.util.Objects;

public final class User {
    private final String userName;
    private String firstName;
    private String lastName;

    public User(final String userName, final String firstName, final String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final var that = (User) obj;
        return Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User[" +
                "userName=" + userName + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }

}
