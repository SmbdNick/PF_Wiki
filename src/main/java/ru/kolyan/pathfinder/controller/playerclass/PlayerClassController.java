package ru.kolyan.pathfinder.controller.playerclass;

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
import ru.kolyan.pathfinder.controller.playerclass.request.AddClassMasteryRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.CreatePlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.UpdateByIdPlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.response.GetAllPlayerClassResponse;
import ru.kolyan.pathfinder.controller.playerclass.response.GetByIdPlayerClassResponse;
import ru.kolyan.pathfinder.service.api.PlayerClassService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/player-class")
@RestController
@RequiredArgsConstructor
public class PlayerClassController {
    private final PlayerClassService playerClassService;

    @PostMapping
    public void create(@RequestBody @Valid CreatePlayerClassRequest request) {
        playerClassService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdPlayerClassResponse getById(@PathVariable UUID id) {
        return playerClassService.getById(id);
    }

    @GetMapping
    public GetAllPlayerClassResponse getAll() {
        return playerClassService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        playerClassService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdPlayerClassRequest request, @PathVariable UUID id) {
        playerClassService.update(request, id);
    }

    @PostMapping("/{id}/class-mastery")
    public void addClassMastery(@RequestBody AddClassMasteryRequest request, @PathVariable UUID id) {
        playerClassService.addClassMastery(request, id);
    }

    @DeleteMapping("/class-mastery/{id}")
    public void deleteClassMastery(@PathVariable UUID id) {
        playerClassService.deleteClassMastery(id);
    }
}



