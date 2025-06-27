package ru.kolyan.pathfinder.controller.skillfeat.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdSkillFeatRequest {
    private String name;
    private String description;
}
