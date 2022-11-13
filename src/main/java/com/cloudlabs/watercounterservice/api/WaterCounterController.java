package com.cloudlabs.watercounterservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlabs.watercounterservice.repo.model.WaterForUser;
import com.cloudlabs.watercounterservice.service.impl.WaterCounterServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/water")
@RestController
public class WaterCounterController {

    private final WaterCounterServiceImpl waterCounterService;

    @GetMapping
    public ResponseEntity<String> showById(@RequestParam double weight) {
        final String recomendedWater = waterCounterService.getWaterByWeight(weight);

        return ResponseEntity.ok(recomendedWater);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WaterForUser> getWaterForUser(@PathVariable long userId) {
        final WaterForUser recomendedWater = waterCounterService.getCurrentWaterInfoByUserId(userId);

        return ResponseEntity.ok(recomendedWater);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<WaterForUser> updateActualWaterForUser(@PathVariable long userId, @RequestParam double waterAdditional) {
        final WaterForUser recomendedWater = waterCounterService.updateWaterActualByUserId(userId, waterAdditional);

        return ResponseEntity.ok(recomendedWater);
    }
}
