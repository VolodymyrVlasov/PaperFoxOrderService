package com.paperfox.order.models;

import java.util.UUID;

public class Customer {
    private UUID customerUUID;
    private long chatID;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;

    public Customer(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

//    public UUID getCustomerUUID() {
//        return customerUUID;
//    }

    public long getChatID() {
        return chatID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
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
