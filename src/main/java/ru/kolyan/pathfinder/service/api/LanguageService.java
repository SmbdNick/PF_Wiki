package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.request.UpdateByIdLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;

import java.util.UUID;

public interface LanguageService {
    void create(CreateLanguageRequest request);

    GetByIdLanguageResponse getById(UUID id);

    GetAllLanguageResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdLanguageRequest request, UUID id);
}
