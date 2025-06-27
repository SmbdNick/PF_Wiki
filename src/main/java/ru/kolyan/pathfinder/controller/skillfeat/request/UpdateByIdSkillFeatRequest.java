package ru.kolyan.pathfinder.controller.skillfeat.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class UpdateByIdSkillFeatRequest {
    private UUID id;
    private String name;
    private String description;

}
