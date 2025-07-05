package ru.kolyan.pathfinder.controller.language;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolyan.pathfinder.controller.language.request.CreateLanguageRequest;
import ru.kolyan.pathfinder.controller.language.request.UpdateByIdLanguageRequest;
import ru.kolyan.pathfinder.controller.language.response.GetAllLanguageResponse;
import ru.kolyan.pathfinder.controller.language.response.GetByIdLanguageResponse;
import ru.kolyan.pathfinder.service.api.LanguageService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/language")
@RestController
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping
    public void create(@Valid @RequestBody CreateLanguageRequest request) {
        languageService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdLanguageResponse getById(@PathVariable UUID id) {
        return languageService.getById(id);
    }

    @GetMapping
    public GetAllLanguageResponse getAll() {
        return languageService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        languageService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdLanguageRequest request, @PathVariable UUID id) {
        languageService.update(request, id);
    }
}
