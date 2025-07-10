package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.CreateAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.UpdateByIdAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.response.GetAllAncestryResponse;
import ru.kolyan.pathfinder.controller.ancestry.response.GetByIdAncestryResponse;
import ru.kolyan.pathfinder.exception.BadRequestException;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Ancestry;
import ru.kolyan.pathfinder.model.AncestryLanguage;
import ru.kolyan.pathfinder.model.AncestryTrait;
import ru.kolyan.pathfinder.repository.AncestryLanguageRepository;
import ru.kolyan.pathfinder.repository.AncestryRepository;
import ru.kolyan.pathfinder.repository.AncestryTraitRepository;
import ru.kolyan.pathfinder.repository.AttributeComboRepository;
import ru.kolyan.pathfinder.service.api.AncestryService;
import ru.kolyan.pathfinder.service.api.LanguageService;
import ru.kolyan.pathfinder.service.api.TraitService;
import ru.kolyan.pathfinder.service.dto.GetLanguageDto;
import ru.kolyan.pathfinder.service.dto.GetLanguageFilterDto;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AncestryServiceImpl implements AncestryService {
    private final AncestryRepository ancestryRepository;
    private final AttributeComboRepository attributeComboRepository;
    private final AncestryTraitRepository ancestryTraitRepository;
    private final TraitService traitService;
    private final AncestryLanguageRepository ancestryLanguageRepository;
    private final LanguageService languageService;

    private static final String ENTITY_ANCESTRY = "Родословная";
    private static final String ENTITY_TRAIT = "Трэйт";
    private static final String ENTITY_LANGUAGE = "Язык";

    @Override
    public void create(CreateAncestryRequest request) {
        Ancestry ancestry = new Ancestry();
        ancestry.setAttributeComboId(request.getAttributeComboId());
        ancestry.setDescription(request.getDescription());
        ancestry.setHp(request.getHp());
        ancestry.setName(request.getName());
        ancestry.setSize(request.getSize());
        ancestry.setSpeed(request.getSpeed());

        try {
            ancestryRepository.save(ancestry);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_ANCESTRY, ancestry.getName()));
        }
    }

    @Override
    @Transactional
    public GetByIdAncestryResponse getById(UUID id) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));

        List<AncestryTrait> ancestryTraitList = ancestryTraitRepository.findAllByAncestryId(id);
        List<UUID> traitIds = ancestryTraitList.stream()
                .map(AncestryTrait::getTraitId)
                .toList();
        List<String> traits = traitService.getAllByFilter(GetTraitsFilterDto.builder().traitIdList(traitIds).build())
                .stream()
                .map(GetTraitDto::getName)
                .toList();

        List<AncestryLanguage> ancestryLanguageList = ancestryLanguageRepository.findAllByAncestryId(id);
        List<UUID> languageIds = ancestryLanguageList.stream()
                .map(AncestryLanguage::getLanguageId)
                .toList();
        List<String> languages = languageService.getAllByFilter(GetLanguageFilterDto.builder().languageIdList(languageIds).build())
                .stream()
                .map(GetLanguageDto::getName)
                .toList();

        return GetByIdAncestryResponse.builder()
                .id(ancestry.getId())
                .name(ancestry.getName())
                .hp(ancestry.getHp())
                .size(ancestry.getSize())
                .speed(ancestry.getSpeed())
                .attributeComboId(ancestry.getAttributeComboId())
                .comboName(getComboName(ancestry.getAttributeComboId()))
                .description(ancestry.getDescription())
                .traitList(traits)
                .languageList(languages)
                .build();
    }

    @Override
    @Transactional
    public GetAllAncestryResponse getAll() {
        List<Ancestry> ancestryList = ancestryRepository.findAll();

        List<GetAllAncestryResponse.Ancestry> content = ancestryList.stream()
                .map(ancestry -> GetAllAncestryResponse.Ancestry.builder()
                        .id(ancestry.getId())
                        .name(ancestry.getName())
                        .hp(ancestry.getHp())
                        .size(ancestry.getSize())
                        .speed(ancestry.getSpeed())
                        .attributeComboId(ancestry.getAttributeComboId())
                        .comboName(getComboName(ancestry.getAttributeComboId()))
                        .description(ancestry.getDescription())
                        .build())
                .toList();

        return GetAllAncestryResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!ancestryRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id));
        }
        ancestryRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdAncestryRequest request, UUID id) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(ancestry::setName);

        Optional.ofNullable(request.getHp())
                .ifPresent(ancestry::setHp);

        Optional.ofNullable(request.getSize())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(ancestry::setSize);

        Optional.ofNullable(request.getSpeed())
                .ifPresent(ancestry::setSpeed);

        Optional.ofNullable(request.getAttributeComboId())
                .ifPresent(ancestry::setAttributeComboId);

        Optional.ofNullable(request.getDescription())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(ancestry::setDescription);

        ancestryRepository.save(ancestry);
    }

    @Override
    @Transactional
    public void addTraits(UUID id, AddAncestryTraitsRequest request) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));

        List<AncestryTrait> ancestryTraitListToSave = new ArrayList<>();

        request.getTraitIdList().forEach(traitId -> {
            AncestryTrait ancestryTrait = new AncestryTrait();
            ancestryTrait.setTraitId(traitId);
            ancestryTrait.setAncestryId(ancestry.getId());

            ancestryTraitListToSave.add(ancestryTrait);
        });

        ancestryTraitRepository.saveAll(ancestryTraitListToSave);
    }

    @Override
    @Transactional
    public void deleteTraits(UUID id, DeleteAncestryTraitsRequest request) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));

        if (!ancestryTraitRepository.existsByAncestryIdAndTraitIdIn(ancestry.getId(), request.getTraitId())) {
            throw new BadRequestException(ErrorMsgConstants.badRequest(ENTITY_TRAIT, ENTITY_ANCESTRY));
        }
        ancestryTraitRepository.deleteAllByAncestryIdAndTraitIdIn(ancestry.getId(), request.getTraitId());
    }

    @Override
    @Transactional
    public void addLanguages(UUID id, AddAncestryLanguageRequest request) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));
        List<AncestryLanguage> ancestryLanguageListToSave = new ArrayList<>();

        request.getLanguageIdList().forEach(languageId -> {
            AncestryLanguage ancestryLanguage = new AncestryLanguage();
            ancestryLanguage.setLanguageId(languageId);
            ancestryLanguage.setAncestryId(ancestry.getId());

            ancestryLanguageListToSave.add(ancestryLanguage);
        });

        ancestryLanguageRepository.saveAll(ancestryLanguageListToSave);
    }

    @Override
    @Transactional
    public void deleteLanguages(UUID id, DeleteAncestryLanguageRequest request) {
        Ancestry ancestry = ancestryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ANCESTRY, id)));

        if (!ancestryLanguageRepository.existsByAncestryIdAndLanguageIdIn(ancestry.getId(), request.getLanguageIdList())) {
            throw new BadRequestException(ErrorMsgConstants.badRequest(ENTITY_LANGUAGE, ENTITY_ANCESTRY));
        }
        ancestryLanguageRepository.deleteAllByAncestryIdAndLanguageIdIn(ancestry.getId(), request.getLanguageIdList());
    }

    private String getComboName(UUID comboId) {
        AtomicReference<String> comboName = new AtomicReference<>("");
        attributeComboRepository.findById(comboId).ifPresent(combo -> comboName.set(combo.getComboName()));

        return comboName.get();
    }
}
