package org.kpn.ch4.custom;

public class FullName {

    private String firstName = "no-name";
    private String lastName;

    public FullName(String... args) {
        int length = args.length;
        if (length > 0){
            firstName = args[0];
        }
        if (length > 1){
            lastName = args[1];
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
