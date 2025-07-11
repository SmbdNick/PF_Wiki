package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolyan.pathfinder.controller.playerclass.request.AddClassMasteryRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.CreatePlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.UpdateByIdPlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.response.GetAllPlayerClassResponse;
import ru.kolyan.pathfinder.controller.playerclass.response.GetByIdPlayerClassResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.mapper.PlayerClassMapper;
import ru.kolyan.pathfinder.model.ClassMastery;
import ru.kolyan.pathfinder.model.PlayerClass;
import ru.kolyan.pathfinder.repository.AttributeComboRepository;
import ru.kolyan.pathfinder.repository.ClassMasteryRepository;
import ru.kolyan.pathfinder.repository.MasteryTierRepository;
import ru.kolyan.pathfinder.repository.PlayerClassRepository;
import ru.kolyan.pathfinder.service.api.PlayerClassService;
import ru.kolyan.pathfinder.service.dto.ClassMasteryDto;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class PlayerClassServiceImpl implements PlayerClassService {
    private final PlayerClassRepository playerClassRepository;
    private final AttributeComboRepository attributeComboRepository;
    private final ClassMasteryRepository classMasteryRepository;
    private final MasteryTierRepository masteryTierRepository;
    private final PlayerClassMapper playerClassMapper;

    private static final String ENTITY_PLAYER_CLASS = "Класс";
    private static final String ENTITY_CLASS_MASTERY = "Мастерство Класса";

    @Override
    public void create(CreatePlayerClassRequest request) {
        PlayerClass playerClass = playerClassMapper.fromCreateDto(request);

        try {
            playerClassRepository.save(playerClass);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_PLAYER_CLASS, playerClass.getName()));
        }
    }

    @Override
    @Transactional
    public GetByIdPlayerClassResponse getById(UUID id) {
        PlayerClass playerClass = playerClassRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_PLAYER_CLASS, id)));

        List<ClassMastery> classMasteryList = classMasteryRepository.findAllByPlayerClassId(id);
        List<ClassMasteryDto> classMasteries = classMasteryList.stream()
                .map(classMastery -> playerClassMapper.fromEntity(classMastery, getMasteryTierName(classMastery.getMasteryTierId())))
                .toList();

        return playerClassMapper.toGetByIdDto(playerClass, getComboName(playerClass.getAttributeComboId()), classMasteries);
    }

    @Override
    @Transactional
    public GetAllPlayerClassResponse getAll() {
        List<PlayerClass> playerClassList = playerClassRepository.findAll();

        List<GetAllPlayerClassResponse.PlayerClass> content = playerClassList.stream()
                .map(playerClass -> GetAllPlayerClassResponse.PlayerClass.builder()
                        .id(playerClass.getId())
                        .name(playerClass.getName())
                        .hpPerLvl(playerClass.getHpPerLvl())
                        .description(playerClass.getDescription())
                        .attributeComboId(playerClass.getAttributeComboId())
                        .attributeComboName(getComboName(playerClass.getAttributeComboId()))
                        .build())
                .toList();

        return GetAllPlayerClassResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!playerClassRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_PLAYER_CLASS, id));
        }
        playerClassRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdPlayerClassRequest request, UUID id) {
        PlayerClass playerClass = playerClassRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_PLAYER_CLASS, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(playerClass::setName);

        Optional.ofNullable(request.getHpPerLvl())
                .ifPresent(playerClass::setHpPerLvl);

        Optional.ofNullable(request.getDescription())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(playerClass::setDescription);

        Optional.ofNullable(request.getAttributeComboId())
                .ifPresent(playerClass::setAttributeComboId);

        playerClassRepository.save(playerClass);
    }

    @Override
    @Transactional
    public void addClassMastery(AddClassMasteryRequest request, UUID id) {
        PlayerClass playerClass = playerClassRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_PLAYER_CLASS, id)));

        ClassMastery classMastery = new ClassMastery();
        classMastery.setPlayerClassId(playerClass.getId());
        classMastery.setMasteryTierId(request.getMasteryTierId());
        classMastery.setCharacteristic(request.getCharacteristic());

        classMasteryRepository.save(classMastery);
    }

    @Override
    public void deleteClassMastery(UUID classMasteryId) {
        if (!classMasteryRepository.existsById(classMasteryId)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_CLASS_MASTERY, classMasteryId));
        }
        classMasteryRepository.deleteById(classMasteryId);
    }

    private String getComboName(UUID comboId) {
        AtomicReference<String> comboName = new AtomicReference<>("");
        attributeComboRepository.findById(comboId).ifPresent(combo -> comboName.set(combo.getComboName()));

        return comboName.get();
    }

    private String getMasteryTierName(UUID masteryTierId) {
        AtomicReference<String> masteryTierName = new AtomicReference<>("");
        masteryTierRepository.findById(masteryTierId).ifPresent(masteryTier -> masteryTierName.set(masteryTier.getName()));

        return masteryTierName.get();
    }
}
