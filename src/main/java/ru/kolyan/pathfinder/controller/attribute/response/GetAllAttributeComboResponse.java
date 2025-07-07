package ru.kolyan.pathfinder.controller.attribute.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllAttributeComboResponse {
    List<AttributeCombo> content;

    @Getter
    @Builder
    @Jacksonized
    public static class AttributeCombo {
        private UUID attributeId1;
        private UUID attributeId2;
        private String comboName;
    }

}
