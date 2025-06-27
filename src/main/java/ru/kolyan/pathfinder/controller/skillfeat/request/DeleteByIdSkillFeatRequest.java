package ru.kolyan.pathfinder.controller.skillfeat.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class DeleteByIdSkillFeatRequest {
    private UUID id;
}
