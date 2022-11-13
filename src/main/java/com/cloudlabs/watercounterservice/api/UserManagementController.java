package com.cloudlabs.watercounterservice.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlabs.watercounterservice.repo.model.User;
import com.cloudlabs.watercounterservice.service.impl.UserManagementServiceImpl;
import com.cloudlabs.watercounterservice.service.impl.WaterCounterServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserManagementController {

    private final UserManagementServiceImpl userManagementService;
    private final WaterCounterServiceImpl waterCounterService;

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        final List<User> users = userManagementService.fetchAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showById(@PathVariable long id) {
        try {
            final User user = userManagementService.fetchUserById(id);

            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.cloudlabs.watercounterservice.api.dto.User user) {
        final String email = user.email();
        final String name = user.name();
        final double weight = user.weight();
        final long userId = userManagementService.createUser(email, name, weight);
        final String userUri = String.format("/users/%d", userId);

        return ResponseEntity.created(URI.create(userUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody com.cloudlabs.watercounterservice.api.dto.User user) {
        final String email = user.email();
        final String name = user.name();
        final double weight = user.weight();

        try {
            userManagementService.updateUser(id, email, name, weight);
            waterCounterService.updateWaterExpectedByUserId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userManagementService.deleteUser(id);
        waterCounterService.deleteWaterForUserById(id);

        return ResponseEntity.noContent().build();
    }
}