package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest;

import com.gonzalofdezz.fitmanager.gym.application.usecases.GymService;
import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto.CreateGymRequest;
import com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto.GymResponse;
import com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto.UpdateGymRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GymResponse create(@RequestBody CreateGymRequest request) {
        Gym created = gymService.create(request.getName(), request.getPlan());
        return new GymResponse(created.getId(), created.getName(), created.getPlan(), created.getCreatedAt());
    }

    @GetMapping
    public List<GymResponse> getAll() {
        return gymService.getAll().stream()
                .map(g -> new GymResponse(g.getId(), g.getName(), g.getPlan(), g.getCreatedAt()))
                .toList();
    }

    @GetMapping("/{id}")
    public GymResponse getById(@PathVariable UUID id) {
        Gym gym = gymService.getById(id);
        return new GymResponse(gym.getId(), gym.getName(), gym.getPlan(), gym.getCreatedAt());
    }

    @PutMapping("/{id}")
    public GymResponse update(@PathVariable UUID id, @RequestBody UpdateGymRequest request) {
        Gym updated = gymService.update(id, request.getName(), request.getPlan());
        return new GymResponse(updated.getId(), updated.getName(), updated.getPlan(), updated.getCreatedAt());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        gymService.delete(id);
    }


}
