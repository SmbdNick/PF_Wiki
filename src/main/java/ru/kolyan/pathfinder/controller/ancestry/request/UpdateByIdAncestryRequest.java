package ru.kolyan.pathfinder.controller.ancestry.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class UpdateByIdAncestryRequest {
    private String name;
    private Integer hp;
    private String size;
    private Integer speed;
    private UUID attributeComboId;
    private String description;
}
