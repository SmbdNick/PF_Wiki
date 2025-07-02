package ru.kolyan.pathfinder.controller.skillfeat;

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
import ru.kolyan.pathfinder.controller.skillfeat.request.AddTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.service.api.SkillFeatService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/skill-feat")
@RestController
@RequiredArgsConstructor
public class SkillFeatController {
    private final SkillFeatService skillFeatService;

    @PostMapping
    public void create(@Valid @RequestBody CreateSkillFeatRequest request) {
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

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        skillFeatService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdSkillFeatRequest request, @PathVariable UUID id){
        skillFeatService.update(request, id);
    }

    @PostMapping("/{id}/add-traits")
    public void addTraits(@RequestBody @Valid AddTraitsRequest request, @PathVariable UUID id){
        skillFeatService.addTraits(id, request);
    }

    @DeleteMapping("/{id}/delete-traits")
    public void deleteTraits(@PathVariable UUID id, @Valid DeleteTraitsRequest request){
        skillFeatService.deleteTraits(id, request);
    }
}
