package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.masterytier.request.CreateMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.request.UpdateByIdMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.response.GetAllMasteryTierResponse;
import ru.kolyan.pathfinder.controller.masterytier.response.GetByIdMasteryTierResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.MasteryTier;
import ru.kolyan.pathfinder.repository.MasteryTierRepository;
import ru.kolyan.pathfinder.service.api.MasteryTierService;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MasteryTierServiceImpl implements MasteryTierService {
    private final MasteryTierRepository masteryTierRepository;

    private static final String ENTITY_MASTERY_TIER = "Уровень Мастерства";

    @Override
    public void create(CreateMasteryTierRequest request) {
        MasteryTier masteryTier = new MasteryTier();
        masteryTier.setName(request.getName());

        try {
            masteryTierRepository.save(masteryTier);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_MASTERY_TIER, masteryTier.getName()));
        }
    }

    @Override
    public GetByIdMasteryTierResponse getById(UUID id) {
        MasteryTier masteryTier = masteryTierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_MASTERY_TIER, id)));

        return GetByIdMasteryTierResponse.builder()
                .id(masteryTier.getId())
                .name(masteryTier.getName())
                .build();
    }

    @Override
    public GetAllMasteryTierResponse getAll() {
        List<MasteryTier> masteryTierList = masteryTierRepository.findAll();
        List<GetAllMasteryTierResponse.MasteryTier> content = masteryTierList.stream()
                .map(masteryTier -> GetAllMasteryTierResponse.MasteryTier.builder()
                        .id(masteryTier.getId())
                        .name(masteryTier.getName())
                        .build())
                .toList();

        return GetAllMasteryTierResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!masteryTierRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_MASTERY_TIER, id));
        }
        masteryTierRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdMasteryTierRequest request, UUID id) {
        MasteryTier masteryTier = masteryTierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_MASTERY_TIER, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(masteryTier::setName);

        masteryTierRepository.save(masteryTier);
    }
}
