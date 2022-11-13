package com.cloudlabs.watercounterservice.service;

import java.time.LocalDate;

import com.cloudlabs.watercounterservice.repo.model.WaterForUser;

public interface WaterCounterService {
    String getWaterByWeight(double weight);
    WaterForUser createCurrentWaterForUser(long userId, double waterExpected, double waterActual, LocalDate date);
    WaterForUser getCurrentWaterInfoByUserId(long userId);
    WaterForUser updateWaterActualByUserId(long userId, double waterAdditional);
    WaterForUser updateWaterExpectedByUserId(long userId);
    void deleteWaterForUserById(long userId);
}
