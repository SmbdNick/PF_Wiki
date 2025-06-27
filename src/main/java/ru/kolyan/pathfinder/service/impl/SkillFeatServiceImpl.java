package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.repository.SkillFeatRepository;
import ru.kolyan.pathfinder.service.api.SkillFeatService;

import java.util.List;
import java.util.Optional;
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

    @Override
    public GetAllSkillFeatResponse getAll() {
        List<SkillFeat> skillFeatList = skillFeatRepository.findAll();
        List<GetAllSkillFeatResponse.SkillFeat> content = skillFeatList.stream()
                .map(skillFeat -> GetAllSkillFeatResponse.SkillFeat.builder()
                        .id(skillFeat.getId())
                        .name(skillFeat.getName())
                        .description(skillFeat.getDescription())
                        .build())
                .toList();

        return GetAllSkillFeatResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void delete(DeleteByIdSkillFeatRequest request) {
        SkillFeat skillFeat = new SkillFeat();
        skillFeat.setId(request.getId());

        skillFeatRepository.deleteById(skillFeat.getId());
    }

    @Override
    public void update(UpdateByIdSkillFeatRequest request) {
        SkillFeat skillFeat = skillFeatRepository.findById(request.getId())
                .orElseThrow(NotFoundException::new);

        Optional.ofNullable(request.getName())
                .ifPresent(skillFeat::setName);

        Optional.ofNullable(request.getDescription())
                .ifPresent(skillFeat::setDescription);

        skillFeatRepository.save(skillFeat);
    }
}
