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
import ru.kolyan.pathfinder.mapper.SkillMapper;
import ru.kolyan.pathfinder.model.Skill;
import ru.kolyan.pathfinder.repository.SkillRepository;
import ru.kolyan.pathfinder.service.api.SkillService;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    private static final String ENTITY_SKILL = "Скил";

    @Override
    public void create(CreateSkillRequest request) {
        Skill skill = skillMapper.fromCreateDto(request);

        try {
            skillRepository.save(skill);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_SKILL, skill.getName()));
        }
    }

    @Override
    public GetByIdSkillResponse getById(UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL, id)));

        return skillMapper.toGetByIdDto(skill);
    }

    @Override
    public GetAllSkillResponse getAll() {
        List<Skill> skillList = skillRepository.findAll();
        List<GetAllSkillResponse.Skill> content = skillList.stream()
                .map(skillMapper::toGetAllContentDto)
                .toList();

        return GetAllSkillResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!skillRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL, id));
        }
        skillRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdSkillRequest request, UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(skill::setName);

        skillRepository.save(skill);
    }
}
