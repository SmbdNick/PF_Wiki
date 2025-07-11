package ru.kolyan.pathfinder.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ClassMasteryDto {
    private String characteristic;
    private String masteryTierName;
}
