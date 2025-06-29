package ru.kolyan.pathfinder.controller.skill.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdSkillResponse {
    private UUID id;
    private String name;
}
