package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeComboResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;

import java.util.UUID;

public interface AttributeService {
    void create(CreateAttributeRequest request);

    GetByIdAttributeResponse getById(UUID id);

    GetAllAttributeResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdAttributeRequest request, UUID id);

    void createCombo(CreateAttributeComboRequest request);

    void updateCombo(UpdateByIdAttributeComboRequest request, UUID id);

    void deleteComboById(UUID id);

    GetAllAttributeComboResponse getAllCombo();
}
