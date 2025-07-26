package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;
import ru.kolyan.pathfinder.model.Language;
import ru.kolyan.pathfinder.service.dto.GetLanguageDto;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(target = "id", ignore = true)
    Language fromCreateDto(CreateLanguageRequest request);

    GetByIdLanguageResponse toGetByIdDto(Language language);

    GetAllLanguageResponse.Language toGetAllContentDto(Language language);

    GetLanguageDto toGetServiceDto(Language language);
}
