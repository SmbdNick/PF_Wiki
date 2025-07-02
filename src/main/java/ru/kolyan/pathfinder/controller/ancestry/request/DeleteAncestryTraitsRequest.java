package ru.kolyan.pathfinder.controller.ancestry.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class DeleteAncestryTraitsRequest {
    private List<UUID> traitId;
}
