package com.cloudlabs.watercounterservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloudlabs.watercounterservice.repo.UserRepo;
import com.cloudlabs.watercounterservice.repo.model.User;
import com.cloudlabs.watercounterservice.service.UserManagementService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepo userRepo;

    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    public User fetchUserById(long id) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if(maybeUser.isPresent())
            return maybeUser.get();
        else
            throw new IllegalArgumentException("Invalid user ID");
    }

    public long createUser(String email, String name, double weight) {
        final User user = new User(email, name, weight);
        final User savedUser = userRepo.save(user);

        return savedUser.getId();
    }

    public void updateUser(long id, String email, String name, double weight) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if(maybeUser.isEmpty())
            throw new IllegalArgumentException("Invalid user ID");

        final User user = maybeUser.get();
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (name != null && !name.isBlank()) user.setName(name);
        if (weight != 0) user.setWeight(weight);
        userRepo.save(user);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }
}
