package ru.kolyan.pathfinder.controller.lore.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateLoreRequest {
    @NotEmpty(message = "Не должно быть пусто длять!")
    private String name;
}
