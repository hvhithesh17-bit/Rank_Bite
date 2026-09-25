package com.rankbite.service;

import com.rankbite.dao.CustomerDAO;
import com.rankbite.dao.OwnerDAO;
import com.rankbite.model.Customer;
import com.rankbite.model.Owner;

public class AuthService {

    private final CustomerDAO customerDAO;
    private final OwnerDAO ownerDAO;

    public AuthService() {
        this.customerDAO = new CustomerDAO();
        this.ownerDAO = new OwnerDAO();
    }

    public AuthService(CustomerDAO customerDAO, OwnerDAO ownerDAO) {
        this.customerDAO = customerDAO;
        this.ownerDAO = ownerDAO;
    }

    // Customer Authentication
    public boolean registerCustomer(Customer customer) {
        if (customer == null || customer.getEmail() == null || customer.getPassword() == null) {
            return false;
        }
        return customerDAO.register(customer);
    }

    public Customer loginCustomer(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return null;
        }
        return customerDAO.login(email.trim(), password);
    }

    // Owner Authentication
    public boolean registerOwner(Owner owner) {
        if (owner == null || owner.getEmail() == null || owner.getPassword() == null) {
            return false;
        }
        return ownerDAO.register(owner);
    }

    public Owner loginOwner(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return null;
        }
        return ownerDAO.login(email.trim(), password);
    }
}
