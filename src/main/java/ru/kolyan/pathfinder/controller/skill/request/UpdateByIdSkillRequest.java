package ru.kolyan.pathfinder.controller.skill.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdSkillRequest {
    private String name;
}
