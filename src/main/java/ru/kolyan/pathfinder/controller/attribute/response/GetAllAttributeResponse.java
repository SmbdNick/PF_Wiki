package ru.kolyan.pathfinder.controller.attribute.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllAttributeResponse {
    private List<Attribute> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Attribute {
        private UUID id;
        private String name;
    }
}
