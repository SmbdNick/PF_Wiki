package ru.kolyan.pathfinder.controller.trait.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdTraitRequest {
    private String name;
    private String description;
}
