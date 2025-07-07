package ru.kolyan.pathfinder.controller.ancestry.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdAncestryResponse {
    private UUID id;
    private String name;
    private Integer hp;
    private String size;
    private Integer speed;
    private UUID attributeComboId;
    private String comboName;
    private String description;
    private List<String> traitList;
    private List<String> languageList;
}
