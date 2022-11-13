package com.cloudlabs.watercounterservice.service;

import java.util.List;

import com.cloudlabs.watercounterservice.repo.model.User;

public interface UserManagementService {

    List<User> fetchAllUsers();
    User fetchUserById(long id) throws IllegalArgumentException;
    long createUser(String email, String name, double weight);
    void updateUser(long id, String email, String name, double weight) throws IllegalArgumentException;
    void deleteUser(long id);
}
