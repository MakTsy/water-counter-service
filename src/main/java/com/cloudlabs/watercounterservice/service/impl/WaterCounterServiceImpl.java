package com.cloudlabs.watercounterservice.service.impl;

import static com.cloudlabs.watercounterservice.utils.Constants.WATER_FOR_KILO_WEIGHT;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloudlabs.watercounterservice.repo.WaterRepo;
import com.cloudlabs.watercounterservice.repo.model.WaterForUser;
import com.cloudlabs.watercounterservice.service.WaterCounterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WaterCounterServiceImpl implements WaterCounterService {

    private final WaterRepo waterRepo;

    private final UserManagementServiceImpl userManagementService;

    public String getWaterByWeight(double weight) {
        return String.format("You need to drink %.2f liters of water per day", calculateWater(weight));
    }

    public WaterForUser createCurrentWaterForUser(long userId, double waterExpected, double waterActual, LocalDate date) {
        final WaterForUser waterForUser = new WaterForUser(userId, waterExpected, waterActual, date);
        final WaterForUser savedWater = waterRepo.save(waterForUser);

        return savedWater;
    }

    public WaterForUser getCurrentWaterInfoByUserId(long userId) {
        final Optional<WaterForUser> maybeWater = waterRepo.findWaterForUserByUserIdAndAndDate(userId, LocalDate.now());

        if(maybeWater.isEmpty())
            return createCurrentWaterForUser(userId, calculateWater(userManagementService.fetchUserById(userId).getWeight()), 0, LocalDate.now());

        return maybeWater.get();

    }

    public WaterForUser updateWaterActualByUserId(long userId, double waterAdditional) {
        final Optional<WaterForUser> maybeWater = waterRepo.findWaterForUserByUserIdAndAndDate(userId, LocalDate.now());

        if(maybeWater.isEmpty())
            return createCurrentWaterForUser(userId, calculateWater(userManagementService.fetchUserById(userId).getWeight()), 0, LocalDate.now());

        final WaterForUser waterForUser = maybeWater.get();
        if (waterAdditional != 0) waterForUser.setWaterActual(waterForUser.getWaterActual()+waterAdditional);
        waterRepo.save(waterForUser);

        return waterForUser;
    }

    public WaterForUser updateWaterExpectedByUserId(long userId) {
        final Optional<WaterForUser> maybeWater = waterRepo.findWaterForUserByUserIdAndAndDate(userId, LocalDate.now());

        if(maybeWater.isEmpty())
            return createCurrentWaterForUser(userId, calculateWater(userManagementService.fetchUserById(userId).getWeight()), 0, LocalDate.now());

        final WaterForUser waterForUser = maybeWater.get();
        waterForUser.setWaterExpected(calculateWater(userManagementService.fetchUserById(userId).getWeight()));
        waterRepo.save(waterForUser);

        return waterForUser;
    }

    public void deleteWaterForUserById(long userId) {
        waterRepo.deleteAllByUserId(userId);
    }

    private double calculateWater(double weight) {
        return weight * WATER_FOR_KILO_WEIGHT;
    }
}
