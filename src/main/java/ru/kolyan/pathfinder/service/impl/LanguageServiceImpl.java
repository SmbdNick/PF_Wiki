package ru.kolyan.pathfinder.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.request.UpdateByIdLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Language;
import ru.kolyan.pathfinder.repository.LanguageRepository;
import ru.kolyan.pathfinder.service.api.LanguageService;
import ru.kolyan.pathfinder.service.dto.GetLanguageDto;
import ru.kolyan.pathfinder.service.dto.GetLanguageFilterDto;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final EntityManager entityManager;

    private static final String ENTITY_LANGUAGE = "Язык";

    @Override
    public void create(CreateLanguageRequest request) {
        Language language = new Language();
        language.setName(request.getName());

        try {
            languageRepository.save(language);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_LANGUAGE, language.getName()));
        }
    }

    @Override
    public GetByIdLanguageResponse getById(UUID id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LANGUAGE, id)));

        return GetByIdLanguageResponse.builder()
                .id(language.getId())
                .name(language.getName())
                .build();
    }

    @Override
    public GetAllLanguageResponse getAll() {
        List<Language> languageList = languageRepository.findAll();
        List<GetAllLanguageResponse.Language> content = languageList.stream()
                .map(language -> GetAllLanguageResponse.Language.builder()
                        .id(language.getId())
                        .name(language.getName())
                        .build())
                .toList();

        return GetAllLanguageResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!languageRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LANGUAGE, id));
        }
        languageRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdLanguageRequest request, UUID id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_LANGUAGE, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(language::setName);

        languageRepository.save(language);
    }

    @Override
    public List<GetLanguageDto> getAllByFilter(GetLanguageFilterDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Language> query = cb.createQuery(Language.class);
        Root<Language> root = query.from(Language.class);

        query.multiselect(root.get("id"), root.get("name"))
                .where(root.get("id").in(filter.getLanguageIdList()));
        List<Language> languages = entityManager.createQuery(query).getResultList();

        return languages.stream()
                .map(language -> GetLanguageDto.builder()
                        .id(language.getId())
                        .name(language.getName())
                        .build()
                )
                .toList();
    }
}
