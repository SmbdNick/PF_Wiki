package ru.kolyan.pathfinder.controller.playerclass.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import ru.kolyan.pathfinder.service.dto.ClassMasteryDto;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdPlayerClassResponse {
    private UUID id;
    private String name;
    private Integer hpPerLvl;
    private String description;
    private UUID attributeComboId;
    private String attributeComboName;
    private List<ClassMasteryDto> classMasteries;
}
