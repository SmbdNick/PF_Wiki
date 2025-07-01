package ru.kolyan.pathfinder.controller.trait;

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
import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.request.UpdateByIdTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;
import ru.kolyan.pathfinder.service.api.TraitService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/trait")
@RestController
@RequiredArgsConstructor
public class TraitController {
    private final TraitService traitService;

    @PostMapping
    public void create(@Valid @RequestBody CreateTraitRequest request) {
        traitService.create(request);
    }

    @GetMapping
    public GetAllTraitResponse getAll() {
        return traitService.getAll();
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdTraitRequest request, @PathVariable UUID id) {
        traitService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        traitService.deleteById(id);
    }
}
