package ru.kolyan.pathfinder.controller.attribute.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class UpdateByIdAttributeComboRequest {
    private UUID attributeId1;
    private UUID attributeId2;
    private String comboName;
}
