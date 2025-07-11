package ru.kolyan.pathfinder.controller.masterytier.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdMasteryTierResponse {
    private UUID id;
    private String name;
}
