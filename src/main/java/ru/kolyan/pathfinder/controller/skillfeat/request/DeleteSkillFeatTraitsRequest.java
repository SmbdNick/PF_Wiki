package ru.kolyan.pathfinder.controller.skillfeat.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class DeleteSkillFeatTraitsRequest {
    private List<UUID> traitId;
}
