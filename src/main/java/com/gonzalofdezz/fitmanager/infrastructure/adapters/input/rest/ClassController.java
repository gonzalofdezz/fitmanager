package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import com.gonzalofdezz.fitmanager.application.dto.ClassResponseDTO;
import com.gonzalofdezz.fitmanager.application.dto.CrearClaseDTO;
import com.gonzalofdezz.fitmanager.application.dto.EditarClaseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Obtiene todas las clases disponibles del gimnasio")
    public List<ClassResponseDTO> getClasses() {
        return getClassesUseCase.execute().stream()
                .map(gymClass -> new ClassResponseDTO(
                        gymClass.id(),
                        gymClass.nombre(),
                        gymClass.descripcion(),
                        gymClass.nivel(),
                        gymClass.duracionMinutos(),
                        gymClass.capacidadPorDefecto()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una clase por su ID")
    public ClassResponseDTO getClassById(@PathVariable Long id) {
        var gymClass = classesInputPort.getClassById(id);
        return new ClassResponseDTO(
                gymClass.id(),
                gymClass.nombre(),
                gymClass.descripcion(),
                gymClass.nivel(),
                gymClass.duracionMinutos(),
                gymClass.capacidadPorDefecto()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una nueva clase")
    public ClassResponseDTO crear(@Valid @RequestBody CrearClaseDTO request) {
        var clase = classesInputPort.crearClase(
                request.nombre(),
                request.descripcion(),
                request.nivel(),
                request.duracionMinutos(),
                request.capacidadPorDefecto()
        );
        return new ClassResponseDTO(
                clase.id(),
                clase.nombre(),
                clase.descripcion(),
                clase.nivel(),
                clase.duracionMinutos(),
                clase.capacidadPorDefecto()
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita una clase existente")
    public ClassResponseDTO editar(@PathVariable Long id, @Valid @RequestBody EditarClaseDTO request) {
        var actualizada = classesInputPort.editarClase(
                id,
                request.nombre(),
                request.descripcion(),
                request.nivel(),
                request.duracionMinutos(),
                request.capacidadPorDefecto()
        );
        return new ClassResponseDTO(
                actualizada.id(),
                actualizada.nombre(),
                actualizada.descripcion(),
                actualizada.nivel(),
                actualizada.duracionMinutos(),
                actualizada.capacidadPorDefecto()
        );
    }
}
