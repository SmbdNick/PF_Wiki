package ru.kolyan.pathfinder.controller.attribute.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateAttributeRequest {
    @NotEmpty(message = "Название атрибута ОБЯЗАТЕЛЬНА!")
    private String name;
}
