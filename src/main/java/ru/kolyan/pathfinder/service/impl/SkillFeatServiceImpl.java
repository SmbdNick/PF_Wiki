package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolyan.pathfinder.controller.skillfeat.request.AddSkillFeatTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteSkillFeatTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.exception.BadRequestException;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.mapper.SkillFeatMapper;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.model.SkillFeatTrait;
import ru.kolyan.pathfinder.repository.SkillFeatRepository;
import ru.kolyan.pathfinder.repository.SkillFeatTraitRepository;
import ru.kolyan.pathfinder.service.api.SkillFeatService;
import ru.kolyan.pathfinder.service.api.TraitService;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillFeatServiceImpl implements SkillFeatService {
    private final SkillFeatRepository skillFeatRepository;
    private final SkillFeatTraitRepository skillFeatTraitRepository;
    private final TraitService traitService;
    private final SkillFeatMapper skillFeatMapper;

    private static final String ENTITY_SKILL_FEAT = "Скил Фит";
    private static final String ENTITY_TRAIT = "Трэйт";

    @Override
    public void create(CreateSkillFeatRequest request) {
        SkillFeat skillFeat = skillFeatMapper.fromCreateDto(request);

        try {
            skillFeatRepository.save(skillFeat);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_SKILL_FEAT, skillFeat.getName()));
        }
    }

    @Override
    @Transactional
    public GetByIdSkillFeatResponse getById(UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL_FEAT, id)));

        List<SkillFeatTrait> skillFeatTraitList = skillFeatTraitRepository.findAllBySkillFeatId(id);
        List<UUID> traitIds = skillFeatTraitList.stream()
                .map(SkillFeatTrait::getTraitId)
                .toList();
        List<String> traits = traitService.getAllByFilter(GetTraitsFilterDto.builder().traitIdList(traitIds).build())
                .stream()
                .map(GetTraitDto::getName)
                .toList();

        return skillFeatMapper.toGetByIdDto(skillFeat, traits);
    }

    @Override
    public GetAllSkillFeatResponse getAll() {
        List<SkillFeat> skillFeatList = skillFeatRepository.findAll();
        List<GetAllSkillFeatResponse.SkillFeat> content = skillFeatList.stream()
                .map(skillFeatMapper::toGetAllContentDto)
                .toList();

        return GetAllSkillFeatResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!skillFeatRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL_FEAT, id));
        }
        skillFeatRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdSkillFeatRequest request, UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL_FEAT, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(skillFeat::setName);

        Optional.ofNullable(request.getDescription())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(skillFeat::setDescription);

        try {
            skillFeatRepository.save(skillFeat);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_SKILL_FEAT, skillFeat.getName()));
        }
    }

    @Transactional
    @Override
    public void addTraits(UUID skillFeatId, AddSkillFeatTraitsRequest request) {
        SkillFeat skillFeat = skillFeatRepository.findById(skillFeatId)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL_FEAT, skillFeatId)));

        List<SkillFeatTrait> skillFeatTraitListToSave = skillFeatMapper.fromAddTraitsDto(request, skillFeat);

        skillFeatTraitRepository.saveAll(skillFeatTraitListToSave);
    }

    @Transactional
    @Override
    public void deleteTraits(UUID id, DeleteSkillFeatTraitsRequest request) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_SKILL_FEAT, id)));

        if (!skillFeatTraitRepository.existsBySkillFeatIdAndTraitIdIn(skillFeat.getId(), request.getTraitId())) {
            throw new BadRequestException(ErrorMsgConstants.badRequest(ENTITY_TRAIT, ENTITY_SKILL_FEAT));
        }
        skillFeatTraitRepository.deleteAllBySkillFeatIdAndTraitIdIn(skillFeat.getId(), request.getTraitId());
    }
}
