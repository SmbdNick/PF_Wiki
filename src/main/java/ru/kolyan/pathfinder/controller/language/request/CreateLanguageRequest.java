package ru.kolyan.pathfinder.controller.language.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateLanguageRequest {
    @NotEmpty(message = "Название Языка обязательно!!!")
    private String name;
}
