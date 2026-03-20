package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest;

import com.gonzalofdezz.fitmanager.gym.application.usecases.GymService;
import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto.GymResponse;
import com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto.UpdateGymRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GymResponse create(@Valid @RequestBody CreateGymRequest request) {
        Gym created = gymService.create(request.getName(), request.getPlan());
        return toResponse(created);
    }

    @GetMapping
    public List<GymResponse> getAll() {
        return gymService.getAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public GymResponse getById(@PathVariable Long id) {
        return toResponse(gymService.getById(id));
    }

    @PutMapping("/{id}")
    public GymResponse update(@PathVariable Long id, @Valid @RequestBody UpdateGymRequest request) {
        Gym updated = gymService.update(id, request.getName(), request.getPlan());
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gymService.delete(id);
    }

    private GymResponse toResponse(Gym gym) {
        return new GymResponse(
                gym.getId(),
                gym.getName(),
                gym.getPlan(),
                gym.getCreatedAt()
        );
    }
}