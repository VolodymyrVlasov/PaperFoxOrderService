package com.paperfox.order.models;

import java.util.UUID;

public class Customer {
    private UUID customerUUID;
    private long chatID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "\n\t\tfirstName='" + firstName + '\'' +
                ",\n\t\tlastName='" + lastName + '\'' +
                ",\n\t\tphoneNumber='" + phoneNumber + '\'' +
                ",\n\t\temail='" + email + '\'' +
                '}';
    }
}
