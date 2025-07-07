package ru.kolyan.pathfinder.controller.ancestry;


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
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.CreateAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.UpdateByIdAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.response.GetAllAncestryResponse;
import ru.kolyan.pathfinder.controller.ancestry.response.GetByIdAncestryResponse;
import ru.kolyan.pathfinder.service.api.AncestryService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/ancestry")
@RestController
@RequiredArgsConstructor
public class AncestryController {
    private final AncestryService ancestryService;

    @PostMapping
    public void create(@Valid @RequestBody CreateAncestryRequest request) {
        ancestryService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdAncestryResponse getById(@PathVariable UUID id) {
        return ancestryService.getById(id);
    }

    @GetMapping
    public GetAllAncestryResponse getAll() {
        return ancestryService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        ancestryService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdAncestryRequest request, @PathVariable UUID id) {
        ancestryService.update(request, id);
    }

    @PostMapping("/{id}/traits")
    public void addTraits(@RequestBody AddAncestryTraitsRequest request, @PathVariable UUID id) {
        ancestryService.addTraits(id, request);
    }

    @DeleteMapping("/{id}/traits")
    public void deleteTraits(@Valid DeleteAncestryTraitsRequest request, @PathVariable UUID id) {
        ancestryService.deleteTraits(id, request);
    }

    @PostMapping("/{id}/languages")
    public void addLanguages(@RequestBody AddAncestryLanguageRequest request, @PathVariable UUID id) {
        ancestryService.addLanguages(id, request);
    }

    @DeleteMapping("/{id}/languages")
    public void deleteLanguages(@Valid DeleteAncestryLanguageRequest request, @PathVariable UUID id) {
        ancestryService.deleteLanguages(id, request);
    }
}
