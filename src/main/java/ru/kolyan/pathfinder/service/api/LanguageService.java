package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.request.UpdateByIdLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;
import ru.kolyan.pathfinder.service.dto.GetLanguageDto;
import ru.kolyan.pathfinder.service.dto.GetLanguageFilterDto;

import java.util.List;
import java.util.UUID;

public interface LanguageService {
    void create(CreateLanguageRequest request);

    GetByIdLanguageResponse getById(UUID id);

    GetAllLanguageResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdLanguageRequest request, UUID id);

    List<GetLanguageDto> getAllByFilter(GetLanguageFilterDto filter);
}
