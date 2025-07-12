package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.lore.request.CreateLoreRequest;
import ru.kolyan.pathfinder.controller.lore.request.UpdateByIdLoreRequest;
import ru.kolyan.pathfinder.controller.lore.response.GetAllLoreResponse;
import ru.kolyan.pathfinder.controller.lore.response.GetByIdLoreResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.mapper.LoreMapper;
import ru.kolyan.pathfinder.model.Lore;
import ru.kolyan.pathfinder.repository.LoreRepository;
import ru.kolyan.pathfinder.service.api.LoreService;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoreServiceImpl implements LoreService {
    private final LoreRepository loreRepository;
    private final LoreMapper loreMapper;

    private static final String ENTITY_LORE = "Знание";

    @Override
    public void create(CreateLoreRequest request) {
        Lore lore = loreMapper.fromCreateDto(request);

        try {
            loreRepository.save(lore);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_LORE, lore.getName()));
        }
    }

    @Override
    public GetByIdLoreResponse getById(UUID id) {
        Lore lore = loreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LORE, id)));

        return loreMapper.toGetByIdDto(lore);
    }

    @Override
    public GetAllLoreResponse getAll() {
        List<Lore> loreList = loreRepository.findAll();
        List<GetAllLoreResponse.Lore> content = loreList.stream()
                .map(loreMapper::toGetAllContentDto)
                .toList();

        return GetAllLoreResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!loreRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LORE, id));
        }
        loreRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdLoreRequest request, UUID id) {
        Lore lore = loreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LORE, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(lore::setName);

        try {
            loreRepository.save(lore);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_LORE, lore.getName()));
        }
    }
}
