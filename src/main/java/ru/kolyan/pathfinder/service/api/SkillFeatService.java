package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;

import java.util.UUID;

public interface SkillFeatService {
    void create(CreateSkillFeatRequest request);
    GetByIdSkillFeatResponse getById(UUID id);
}
