package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.application.dto.ClassResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clases")
@Tag(name = "Clases")
public class ClassController {

    private final GetClassesUseCase getClassesUseCase;

    public ClassController(GetClassesUseCase getClassesUseCase) {
        this.getClassesUseCase = getClassesUseCase;
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las clases disponibles del gimnasio")
    public List<ClassResponse> getClasses() {
        return getClassesUseCase.execute().stream()
                .map(gymClass -> new ClassResponse(
                        gymClass.id(),
                        gymClass.name(),
                        gymClass.description(),
                        gymClass.level(),
                        gymClass.durationMinutes(),
                        gymClass.defaultCapacity()
                ))
                .toList();
    }
}
