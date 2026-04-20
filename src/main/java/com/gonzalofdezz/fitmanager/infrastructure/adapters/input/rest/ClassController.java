package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import com.gonzalofdezz.fitmanager.application.dto.ClassResponseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clases")
@Tag(name = "Clases")
public class ClassController {

    private final GetClassesUseCase getClassesUseCase;
    private final ClassesInputPort classesInputPort;

    public ClassController(GetClassesUseCase getClassesUseCase, ClassesInputPort classesInputPort) {
        this.getClassesUseCase = getClassesUseCase;
        this.classesInputPort = classesInputPort;
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las clases predeterminadas disponibles del gimnasio")
    public List<ClassResponseDTO> getClasses() {
        return getClassesUseCase.execute().stream()
                .map(gymClass -> new ClassResponseDTO(
                        gymClass.id(),
                        gymClass.nombre(),
                        gymClass.descripcion(),
                        gymClass.nivel(),
                        gymClass.duracionMinutos(),
                        gymClass.capacidadPorDefecto(),
                        gymClass.diaSemana(),
                        gymClass.fecha()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una clase predeterminada por su ID")
    public ClassResponseDTO getClassById(@PathVariable Long id) {
        var gymClass = classesInputPort.getClassById(id);
        return new ClassResponseDTO(
                gymClass.id(),
                gymClass.nombre(),
                gymClass.descripcion(),
                gymClass.nivel(),
                gymClass.duracionMinutos(),
                gymClass.capacidadPorDefecto(),
                gymClass.diaSemana(),
                gymClass.fecha()
        );
    }

}
