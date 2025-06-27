package ru.kolyan.pathfinder.controller.skillfeat.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateSkillFeatRequest {
    @NotEmpty(message = "Название Скил Фита ОБЯЗАТЕЛЬНО!!!")
    private String name;
    private String description;
}
