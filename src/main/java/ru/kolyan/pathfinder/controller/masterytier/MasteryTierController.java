package ru.kolyan.pathfinder.controller.masterytier;

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
import ru.kolyan.pathfinder.controller.masterytier.request.CreateMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.request.UpdateByIdMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.response.GetAllMasteryTierResponse;
import ru.kolyan.pathfinder.controller.masterytier.response.GetByIdMasteryTierResponse;
import ru.kolyan.pathfinder.service.api.MasteryTierService;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix.public}/mastery-tier")
@RequiredArgsConstructor
public class MasteryTierController {
    private final MasteryTierService masteryTierService;

    @PostMapping
    public void create(@RequestBody @Valid CreateMasteryTierRequest request) {
        masteryTierService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdMasteryTierResponse getById(@PathVariable UUID id) {
        return masteryTierService.getById(id);
    }

    @GetMapping
    public GetAllMasteryTierResponse getAll() {
        return masteryTierService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        masteryTierService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdMasteryTierRequest request, @PathVariable UUID id) {
        masteryTierService.update(request, id);
    }
}
