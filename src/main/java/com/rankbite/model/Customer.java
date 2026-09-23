package com.rankbite.model;

public class Customer extends Person {
    private String phone;

    public Customer(int customerId, String name, String phone) {
        super(customerId, name);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer ID: " + getId() + ", Name: " + getName() + 
                           ", Phone: " + phone);
    }
}
