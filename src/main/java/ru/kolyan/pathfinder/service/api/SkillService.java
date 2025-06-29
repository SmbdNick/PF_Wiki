package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.skill.request.CreateSkillRequest;
import ru.kolyan.pathfinder.controller.skill.request.UpdateByIdSkillRequest;
import ru.kolyan.pathfinder.controller.skill.response.GetAllSkillResponse;
import ru.kolyan.pathfinder.controller.skill.response.GetByIdSkillResponse;

import java.util.UUID;

public interface SkillService {
    void create(CreateSkillRequest request);

    GetByIdSkillResponse getById(UUID id);

    GetAllSkillResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdSkillRequest request, UUID id);
}
