package ru.kolyan.pathfinder.controller.trait.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateTraitRequest {
    @NotEmpty(message = "Название Трэйта ОБЯЗАТЕЛЬНО!!!")
    private String name;
    private String description;
}
