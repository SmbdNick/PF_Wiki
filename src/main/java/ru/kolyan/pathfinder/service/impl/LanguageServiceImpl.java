package ru.kolyan.pathfinder.service.impl;

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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public void create(CreateLanguageRequest request) {
        Language language = new Language();
        language.setName(request.getName());

        try {
            languageRepository.save(language);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Язык с таким назаванием уже ест");
        }
    }

    @Override
    public GetByIdLanguageResponse getById(UUID id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Языка с таким ID нету"));

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
            throw new NotFoundException("Языка с таким ID нету");
        }
        languageRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdLanguageRequest request, UUID id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Языка с таким ID нету"));

        Optional.ofNullable(request.getName())
                .ifPresent(language::setName);

        languageRepository.save(language);
    }
}
