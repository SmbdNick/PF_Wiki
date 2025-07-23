package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.masterytier.request.CreateMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.response.GetAllMasteryTierResponse;
import ru.kolyan.pathfinder.controller.masterytier.response.GetByIdMasteryTierResponse;
import ru.kolyan.pathfinder.model.MasteryTier;

@Mapper(componentModel = "spring")
public interface MasteryTierMapper {
    @Mapping(target = "id", ignore = true)
    MasteryTier fromCreateDto(CreateMasteryTierRequest request);

    GetByIdMasteryTierResponse toGetByIdDto(MasteryTier masteryTier);

    GetAllMasteryTierResponse.MasteryTier toGetAllContentDto(MasteryTier masteryTier);
}
