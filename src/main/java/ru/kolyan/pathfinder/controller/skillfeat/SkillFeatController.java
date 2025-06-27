package ru.kolyan.pathfinder.controller.skillfeat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
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

    @GetMapping
    public GetAllSkillFeatResponse getAll() {
        return skillFeatService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestBody DeleteByIdSkillFeatRequest request){
        skillFeatService.delete(request);
    }

    @PatchMapping
    public void update(@RequestBody UpdateByIdSkillFeatRequest request){
        skillFeatService.update(request);
    }
}
