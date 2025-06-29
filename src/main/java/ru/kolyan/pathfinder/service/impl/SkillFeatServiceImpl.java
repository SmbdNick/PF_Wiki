package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
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

        try {
            skillFeatRepository.save(skillFeat);
        } catch (DataIntegrityViolationException e){
            throw new ConflictException("Скил Фит с таким именем уже ест");
        }
    }

    @Override
    public GetByIdSkillFeatResponse getById(UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Скил Фита с таким ID нету"));

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
    public void deleteById(UUID id) {
        if(!skillFeatRepository.existsById(id)){
            throw new NotFoundException("Скил Фита с таким ID нету");
        }
        skillFeatRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdSkillFeatRequest request, UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Скил Фита с таким ID нету"));

        Optional.ofNullable(request.getName())
                .ifPresent(skillFeat::setName);

        Optional.ofNullable(request.getDescription())
                .ifPresent(skillFeat::setDescription);

        skillFeatRepository.save(skillFeat);
    }
}
