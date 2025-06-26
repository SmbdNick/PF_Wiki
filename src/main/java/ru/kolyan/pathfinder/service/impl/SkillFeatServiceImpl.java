package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.repository.SkillFeatRepository;
import ru.kolyan.pathfinder.service.api.SkillFeatService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillFeatServiceImpl implements SkillFeatService {
    private final SkillFeatRepository skillFeatRepository;

    @Override
    public void create(CreateSkillFeatRequest request) {
        SkillFeat skillFeat = new SkillFeat();
        skillFeat.setName(request.getName());
        skillFeat.setDescription(request.getDescription());

        skillFeatRepository.save(skillFeat);
    }

    @Override
    public GetByIdSkillFeatResponse getById(UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return GetByIdSkillFeatResponse.builder()
                .id(skillFeat.getId())
                .name(skillFeat.getName())
                .description(skillFeat.getDescription())
                .build();
    }
}
