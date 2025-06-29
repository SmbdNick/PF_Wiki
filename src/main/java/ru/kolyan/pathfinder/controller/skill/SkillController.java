package ru.kolyan.pathfinder.controller.skill;

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
import ru.kolyan.pathfinder.controller.skill.request.CreateSkillRequest;
import ru.kolyan.pathfinder.controller.skill.request.UpdateByIdSkillRequest;
import ru.kolyan.pathfinder.controller.skill.response.GetAllSkillResponse;
import ru.kolyan.pathfinder.controller.skill.response.GetByIdSkillResponse;
import ru.kolyan.pathfinder.service.api.SkillService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/skill")
@RestController
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @PostMapping
    public void create(@Valid @RequestBody CreateSkillRequest request) {
        skillService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdSkillResponse getById(@PathVariable UUID id) {
        return skillService.getById(id);
    }

    @GetMapping
    public GetAllSkillResponse getAll() {
        return skillService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        skillService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdSkillRequest request, @PathVariable UUID id) {
        skillService.update(request, id);
    }
}
