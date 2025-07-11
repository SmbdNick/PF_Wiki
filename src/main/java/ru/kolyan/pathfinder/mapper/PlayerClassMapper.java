package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.playerclass.request.CreatePlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.response.GetByIdPlayerClassResponse;
import ru.kolyan.pathfinder.model.ClassMastery;
import ru.kolyan.pathfinder.model.PlayerClass;
import ru.kolyan.pathfinder.service.dto.ClassMasteryDto;

import java.util.List;

@Component
public class PlayerClassMapper {
    public PlayerClass fromCreateDto (CreatePlayerClassRequest request) {
        PlayerClass playerClass = new PlayerClass();
        playerClass.setName(request.getName());
        playerClass.setDescription(request.getDescription());
        playerClass.setHpPerLvl(request.getHpPerLvl());
        playerClass.setAttributeComboId(request.getAttributeComboId());

        return playerClass;
    }

    public GetByIdPlayerClassResponse toGetByIdDto (PlayerClass playerClass, String attributeComboName, List<ClassMasteryDto> classMasteries) {
        return GetByIdPlayerClassResponse.builder()
                .id(playerClass.getId())
                .name(playerClass.getName())
                .hpPerLvl(playerClass.getHpPerLvl())
                .description(playerClass.getDescription())
                .attributeComboId(playerClass.getAttributeComboId())
                .attributeComboName(attributeComboName)
                .classMasteries(classMasteries.stream().map(this::toGetByIdClassMasteryDto).toList())
                .build();
    }

    public GetByIdPlayerClassResponse.ClassMastery toGetByIdClassMasteryDto(ClassMasteryDto dto) {
        return GetByIdPlayerClassResponse.ClassMastery.builder()
                .characteristic(dto.getCharacteristic())
                .masteryTierName(dto.getMasteryTierName())
                .build();
    }

    public ClassMasteryDto fromEntity(ClassMastery classMastery, String masteryTierName) {
        return ClassMasteryDto.builder()
                .characteristic(classMastery.getCharacteristic())
                .masteryTierName(masteryTierName)
                .build();
    }
}
