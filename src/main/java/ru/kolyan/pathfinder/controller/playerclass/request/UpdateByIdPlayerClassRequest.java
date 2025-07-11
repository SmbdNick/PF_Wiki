package ru.kolyan.pathfinder.controller.playerclass.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class UpdateByIdPlayerClassRequest {
    private String name;
    private Integer hpPerLvl;
    private String description;
    private UUID attributeComboId;
}
