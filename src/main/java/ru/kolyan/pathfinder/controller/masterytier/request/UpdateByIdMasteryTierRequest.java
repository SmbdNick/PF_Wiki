package ru.kolyan.pathfinder.controller.masterytier.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdMasteryTierRequest {
    private String name;
}
