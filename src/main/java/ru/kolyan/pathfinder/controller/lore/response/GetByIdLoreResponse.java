package ru.kolyan.pathfinder.controller.lore.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdLoreResponse {
    private UUID id;
    private String name;
}
