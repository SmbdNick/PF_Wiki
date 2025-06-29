package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.skill.request.CreateSkillRequest;
import ru.kolyan.pathfinder.controller.skill.request.UpdateByIdSkillRequest;
import ru.kolyan.pathfinder.controller.skill.response.GetAllSkillResponse;
import ru.kolyan.pathfinder.controller.skill.response.GetByIdSkillResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Skill;
import ru.kolyan.pathfinder.repository.SkillRepository;
import ru.kolyan.pathfinder.service.api.SkillService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    @Override
    public void create(CreateSkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.getName());

        try {
            skillRepository.save(skill);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Скил с таким назаванием уже ест");
        }
    }

    @Override
    public GetByIdSkillResponse getById(UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Скила с таким ID нету"));

        return GetByIdSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .build();
    }

    @Override
    public GetAllSkillResponse getAll() {
        List<Skill> skillList = skillRepository.findAll();
        List<GetAllSkillResponse.Skill> content = skillList.stream()
                .map(skill -> GetAllSkillResponse.Skill.builder()
                        .id(skill.getId())
                        .name(skill.getName())
                        .build())
                .toList();

        return GetAllSkillResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!skillRepository.existsById(id)) {
            throw new NotFoundException("Скила с таким ID нету");
        }
        skillRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdSkillRequest request, UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Скила с таким ID нету"));

        Optional.ofNullable(request.getName())
                .ifPresent(skill::setName);

        skillRepository.save(skill);
    }
}
