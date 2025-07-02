package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolyan.pathfinder.controller.skillfeat.request.AddTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.exception.BadRequestException;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.model.SkillFeatTrait;
import ru.kolyan.pathfinder.repository.SkillFeatRepository;
import ru.kolyan.pathfinder.repository.SkillFeatTraitRepository;
import ru.kolyan.pathfinder.service.api.SkillFeatService;
import ru.kolyan.pathfinder.service.api.TraitService;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillFeatServiceImpl implements SkillFeatService {
    private final SkillFeatRepository skillFeatRepository;
    private final SkillFeatTraitRepository skillFeatTraitRepository;
    private final TraitService traitService;

    private static final String SKILL_FEAT_NOT_FOUND_MSG = "Скил Фита с таким ID нету";

    @Override
    public void create(CreateSkillFeatRequest request) {
        SkillFeat skillFeat = new SkillFeat();
        skillFeat.setName(request.getName());
        skillFeat.setDescription(request.getDescription());

        try {
            skillFeatRepository.save(skillFeat);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Скил Фит с таким именем уже ест");
        }
    }

    @Override
    public GetByIdSkillFeatResponse getById(UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SKILL_FEAT_NOT_FOUND_MSG));

        List<SkillFeatTrait> skillFeatTraitList = skillFeatTraitRepository.findAllBySkillFeatId(id);
        List<UUID> traitIds = skillFeatTraitList.stream()
                .map(SkillFeatTrait::getTraitId)
                .toList();
        List<String> traits = traitService.getAllByFilter(GetTraitsFilterDto.builder().traitIdList(traitIds).build())
                .stream()
                .map(GetTraitDto::getName)
                .toList();

        return GetByIdSkillFeatResponse.builder()
                .id(skillFeat.getId())
                .name(skillFeat.getName())
                .description(skillFeat.getDescription())
                .traitList(traits)
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
        if (!skillFeatRepository.existsById(id)) {
            throw new NotFoundException(SKILL_FEAT_NOT_FOUND_MSG);
        }
        skillFeatRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdSkillFeatRequest request, UUID id) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SKILL_FEAT_NOT_FOUND_MSG));

        Optional.ofNullable(request.getName())
                .ifPresent(skillFeat::setName);

        Optional.ofNullable(request.getDescription())
                .ifPresent(skillFeat::setDescription);

        skillFeatRepository.save(skillFeat);
    }

    @Transactional
    @Override
    public void addTraits(UUID skillFeatId, AddTraitsRequest request) {
        SkillFeat skillFeat = skillFeatRepository.findById(skillFeatId)
                .orElseThrow(() -> new NotFoundException(SKILL_FEAT_NOT_FOUND_MSG));

        List<SkillFeatTrait> skillFeatTraitListToSave = new ArrayList<>();

        request.getTraitIdList().forEach(traitId -> {
            SkillFeatTrait skillFeatTrait = new SkillFeatTrait();
            skillFeatTrait.setTraitId(traitId);
            skillFeatTrait.setSkillFeatId(skillFeat.getId());

            skillFeatTraitListToSave.add(skillFeatTrait);
        });

        skillFeatTraitRepository.saveAll(skillFeatTraitListToSave);
    }

    @Transactional
    @Override
    public void deleteTraits(UUID id, DeleteTraitsRequest request) {
        SkillFeat skillFeat = skillFeatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SKILL_FEAT_NOT_FOUND_MSG));

        if (!skillFeatTraitRepository.existsBySkillFeatIdAndTraitIdIn(skillFeat.getId(), request.getTraitId())){
            throw new BadRequestException("Такого трэйта в этом скил фите нема");
        }
        skillFeatTraitRepository.deleteAllBySkillFeatIdAndTraitIdIn(skillFeat.getId(), request.getTraitId());
    }
}
