package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolyan.pathfinder.controller.background.request.CreateBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.request.UpdateByIdBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.response.GetAllBackgroundResponse;
import ru.kolyan.pathfinder.controller.background.response.GetByIdBackgroundResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.mapper.BackgroundMapper;
import ru.kolyan.pathfinder.model.Background;
import ru.kolyan.pathfinder.repository.AttributeComboRepository;
import ru.kolyan.pathfinder.repository.BackgroundRepository;
import ru.kolyan.pathfinder.repository.LoreRepository;
import ru.kolyan.pathfinder.repository.SkillFeatRepository;
import ru.kolyan.pathfinder.repository.SkillRepository;
import ru.kolyan.pathfinder.service.api.BackgroundService;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class BackgroundServiceImpl implements BackgroundService {
    private final BackgroundRepository backgroundRepository;
    private final SkillFeatRepository skillFeatRepository;
    private final SkillRepository skillRepository;
    private final LoreRepository loreRepository;
    private final AttributeComboRepository attributeComboRepository;
    private final BackgroundMapper backgroundMapper;

    private static final String ENTITY_BACKGROUND = "Предыстория";

    @Override
    public void create(CreateBackgroundRequest request) {
        Background background = backgroundMapper.fromCreateDto(request);

        try {
            backgroundRepository.save(background);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_BACKGROUND, background.getName()));
        }
    }

    @Override
    @Transactional
    public GetByIdBackgroundResponse getById(UUID id) {
        Background background = backgroundRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_BACKGROUND, id)));

        return backgroundMapper.toGetByIdDto(background, getSkillFeatName(background.getSkillFeatId()),
                getSkillName(background.getSkillId()), getLoreName(background.getLoreId()), getComboName(background.getAttributeComboId()));
    }

    @Override
    @Transactional
    public GetAllBackgroundResponse getAll() {
        List<Background> backgroundList = backgroundRepository.findAll();
        List<GetAllBackgroundResponse.Background> content = backgroundList.stream()
                .map(background -> backgroundMapper.getAllContentDto(background, getSkillFeatName(background.getSkillFeatId()),
                        getSkillName(background.getSkillId()), getLoreName(background.getLoreId()), getComboName(background.getAttributeComboId())))
                .toList();

        return GetAllBackgroundResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!backgroundRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_BACKGROUND, id));
        }
        backgroundRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdBackgroundRequest request, UUID id) {
        Background background = backgroundRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_BACKGROUND, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(background::setName);

        Optional.ofNullable(request.getDescription())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(background::setDescription);

        Optional.ofNullable(request.getAttributeComboId())
                .ifPresent(background::setAttributeComboId);

        Optional.ofNullable(request.getLoreId())
                .ifPresent(background::setLoreId);

        Optional.ofNullable(request.getSkillFeatId())
                .ifPresent(background::setSkillFeatId);

        Optional.ofNullable(request.getSkillId())
                .ifPresent(background::setSkillId);

        try {
            backgroundRepository.save(background);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_BACKGROUND, background.getName()));
        }
    }

    private String getSkillFeatName(UUID skillFeatId) {
        AtomicReference<String> skillFeatName = new AtomicReference<>("");
        skillFeatRepository.findById(skillFeatId).ifPresent(skillFeat -> skillFeatName.set(skillFeat.getName()));

        return skillFeatName.get();
    }

    private String getSkillName(UUID skillId) {
        AtomicReference<String> skillName = new AtomicReference<>("");
        skillRepository.findById(skillId).ifPresent(skill -> skillName.set(skill.getName()));

        return skillName.get();
    }

    private String getLoreName(UUID loreId) {
        AtomicReference<String> loreName = new AtomicReference<>("");
        loreRepository.findById(loreId).ifPresent(lore -> loreName.set(lore.getName()));

        return loreName.get();
    }

    private String getComboName(UUID comboId) {
        AtomicReference<String> comboName = new AtomicReference<>("");
        attributeComboRepository.findById(comboId).ifPresent(combo -> comboName.set(combo.getComboName()));

        return comboName.get();
    }
}
