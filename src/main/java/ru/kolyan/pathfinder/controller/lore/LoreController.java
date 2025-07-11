package ru.kolyan.pathfinder.controller.lore;

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
import ru.kolyan.pathfinder.controller.lore.request.CreateLoreRequest;
import ru.kolyan.pathfinder.controller.lore.request.UpdateByIdLoreRequest;
import ru.kolyan.pathfinder.controller.lore.response.GetAllLoreResponse;
import ru.kolyan.pathfinder.controller.lore.response.GetByIdLoreResponse;
import ru.kolyan.pathfinder.service.api.LoreService;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix.public}/lore")
@RequiredArgsConstructor
public class LoreController {
    private final LoreService loreService;

    @PostMapping
    public void create(@Valid @RequestBody CreateLoreRequest request) {
        loreService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdLoreResponse getById(@PathVariable UUID id) {
        return loreService.getById(id);
    }

    @GetMapping
    public GetAllLoreResponse getAll() {
        return loreService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        loreService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdLoreRequest request, @PathVariable UUID id) {
        loreService.update(request, id);
    }
}
