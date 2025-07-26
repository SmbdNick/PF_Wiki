package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.kolyan.pathfinder.controller.playerclass.request.AddClassMasteryRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.CreatePlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.response.GetAllPlayerClassResponse;
import ru.kolyan.pathfinder.controller.playerclass.response.GetByIdPlayerClassResponse;
import ru.kolyan.pathfinder.model.ClassMastery;
import ru.kolyan.pathfinder.model.PlayerClass;
import ru.kolyan.pathfinder.service.dto.ClassMasteryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerClassMapper {
    @Mapping(target = "id", ignore = true)
    PlayerClass fromCreateDto(CreatePlayerClassRequest request);

    @Mapping(source = "classMasteries", target = "classMasteries", qualifiedByName = "toGetByIdClassMasteryDto")
    GetByIdPlayerClassResponse toGetByIdDto(PlayerClass playerClass, String attributeComboName, List<ClassMasteryDto> classMasteries);

    @Named("toGetByIdClassMasteryDto")
    GetByIdPlayerClassResponse.ClassMastery toGetByIdClassMasteryDto(ClassMasteryDto dto);

    ClassMasteryDto fromEntity(ClassMastery classMastery, String masteryTierName);

    GetAllPlayerClassResponse.PlayerClass toGetAllContentDto(PlayerClass playerClass, String attributeComboName);

    @Mapping(source = "playerClass.id", target = "playerClassId")
    @Mapping(target = "id", ignore = true)
    ClassMastery fromAddDtoClassMastery(AddClassMasteryRequest request, PlayerClass playerClass);
}
