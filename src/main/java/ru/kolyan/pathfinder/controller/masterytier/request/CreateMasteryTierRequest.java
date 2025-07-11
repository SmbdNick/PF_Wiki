package ru.kolyan.pathfinder.controller.masterytier.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateMasteryTierRequest {
    @NotEmpty(message = "Свято место пусто не бывает!")
    private String name;
}
