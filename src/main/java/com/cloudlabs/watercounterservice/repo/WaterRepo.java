package com.cloudlabs.watercounterservice.repo;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloudlabs.watercounterservice.repo.model.WaterForUser;

public interface WaterRepo extends JpaRepository<WaterForUser, Long> {
    @Query
    Optional<WaterForUser> findWaterForUserByUserIdAndAndDate(long userId, LocalDate date);

    @Query
    void deleteAllByUserId(long userId);
}
