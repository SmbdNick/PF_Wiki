package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;
import ru.kolyan.pathfinder.model.Language;
import ru.kolyan.pathfinder.service.dto.GetLanguageDto;

@Component
public class LanguageMapper {
    public Language fromCreateDto(CreateLanguageRequest request) {
        Language language = new Language();

        language.setName(request.getName());

        return language;
    }

    public GetByIdLanguageResponse toGetByIdDto(Language language) {
        return GetByIdLanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public GetAllLanguageResponse.Language toGetAllContentDto(Language language) {
        return GetAllLanguageResponse.Language.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    public GetLanguageDto toGetServiceDto(Language language) {
        return GetLanguageDto.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }
}
