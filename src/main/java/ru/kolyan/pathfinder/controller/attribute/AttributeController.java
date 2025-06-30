package ru.kolyan.pathfinder.controller.attribute;

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
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;
import ru.kolyan.pathfinder.service.api.AttributeService;

import java.util.UUID;

@RequestMapping("${api.prefix.public}/attribute")
@RestController
@RequiredArgsConstructor
public class AttributeController {
    private final AttributeService attributeService;

    @PostMapping
    public void create(@Valid @RequestBody CreateAttributeRequest request) {
        attributeService.create(request);
    }

    @GetMapping("/{id}")
    public GetByIdAttributeResponse getById(@PathVariable UUID id) {
        return attributeService.getById(id);
    }

    @GetMapping
    public GetAllAttributeResponse getAll() {
        return attributeService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        attributeService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestBody UpdateByIdAttributeRequest request, @PathVariable UUID id) {
        attributeService.update(request, id);
    }
}
