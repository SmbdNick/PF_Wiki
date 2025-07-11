package ru.kolyan.pathfinder.controller.lore.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdLoreRequest {
    private String name;
}
