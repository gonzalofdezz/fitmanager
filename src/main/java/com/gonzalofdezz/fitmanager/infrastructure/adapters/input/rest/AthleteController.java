package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.ports.input.CreateAthleteUseCase;
import com.gonzalofdezz.fitmanager.application.ports.input.GetAthleteUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.Athlete;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos.request.CreateAthleteRequest;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos.response.AthleteResponse;
import com.gonzalofdezz.fitmanager.infrastructure.mapper.AthleteRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/athletes")
public class AthleteController {

    private final CreateAthleteUseCase createAthleteUseCase;
    private final GetAthleteUseCase getAthleteUseCase;
    private final AthleteRestMapper mapper;

    public AthleteController(CreateAthleteUseCase createAthleteUseCase,
                             GetAthleteUseCase getAthleteUseCase,
                             AthleteRestMapper mapper) {
        this.createAthleteUseCase = createAthleteUseCase;
        this.getAthleteUseCase = getAthleteUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AthleteResponse create(@Valid @RequestBody CreateAthleteRequest request) {
        Athlete athlete = createAthleteUseCase.create(request.name(), request.age());
        return mapper.toResponse(athlete);
    }

    @GetMapping("/{id}")
    public AthleteResponse getById(@PathVariable UUID id) {
        return mapper.toResponse(getAthleteUseCase.getById(id));
    }

    @GetMapping
    public List<AthleteResponse> list() {
        return getAthleteUseCase.findAll().stream().map(mapper::toResponse).toList();
    }
}
