package ru.kolyan.pathfinder.controller.background;

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
import ru.kolyan.pathfinder.controller.background.request.CreateBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.request.UpdateByIdBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.response.GetAllBackgroundResponse;
import ru.kolyan.pathfinder.controller.background.response.GetByIdBackgroundResponse;
import ru.kolyan.pathfinder.service.api.BackgroundService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/background")
@RestController
@RequiredArgsConstructor
public class BackgroundController {
    private final BackgroundService backgroundService;

    @PostMapping
    public void create(@RequestBody @Valid CreateBackgroundRequest request) {
        backgroundService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdBackgroundResponse getById(@PathVariable UUID id) {
        return backgroundService.getById(id);
    }

    @GetMapping
    public GetAllBackgroundResponse getAll() {
        return backgroundService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        backgroundService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody UpdateByIdBackgroundRequest request) {
        backgroundService.update(request, id);
    }
}
