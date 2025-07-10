package ru.kolyan.pathfinder.controller.background.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class UpdateByIdBackgroundRequest {
    private String name;
    private String description;
    private UUID skillFeatId;
    private UUID skillId;
    private UUID loreId;
    private UUID attributeComboId;
}
