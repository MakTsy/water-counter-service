package com.cloudlabs.watercounterservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudlabs.watercounterservice.repo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
