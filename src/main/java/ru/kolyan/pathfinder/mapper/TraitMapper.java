package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;
import ru.kolyan.pathfinder.model.Trait;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;

@Mapper(componentModel = "spring")
public interface TraitMapper {
    @Mapping(target = "id", ignore = true)
    Trait fromCreateDto(CreateTraitRequest request);

    GetAllTraitResponse.Trait toGetAllContentDto(Trait trait);

    GetTraitDto toGetServiceDto(Trait trait);
}
