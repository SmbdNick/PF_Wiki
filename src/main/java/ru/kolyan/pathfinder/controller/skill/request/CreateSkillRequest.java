package ru.kolyan.pathfinder.controller.skill.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CreateSkillRequest {
    @NotEmpty(message = "Название Скила обязательно!!!")
    private String name;
}
