package com.cloudlabs.watercounterservice.api.dto;

import java.time.LocalDate;

public record WaterForUser(double waterExpected, double waterActual, LocalDate date) {
}
