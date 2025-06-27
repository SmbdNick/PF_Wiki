package ru.kolyan.pathfinder.controller.skillfeat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.service.api.SkillFeatService;

import java.util.UUID;

@RequestMapping("/api/skill-feat")
@RestController
@RequiredArgsConstructor
public class SkillFeatController {
    private final SkillFeatService skillFeatService;

    @PostMapping
    public void create(@RequestBody CreateSkillFeatRequest request) {
        skillFeatService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdSkillFeatResponse getById(@PathVariable UUID id) {
        return skillFeatService.getById(id);
    }
}
