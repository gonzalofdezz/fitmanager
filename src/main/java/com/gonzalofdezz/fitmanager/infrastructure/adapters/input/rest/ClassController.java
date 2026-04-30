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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
                        gymClass.capacidadPorDefecto(),
                        gymClass.diaSemana(),
                        gymClass.fecha()
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
                gymClass.capacidadPorDefecto(),
                gymClass.diaSemana(),
                gymClass.fecha()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Crea una nueva clase (solo MANAGER)")
    public ClassResponseDTO crearClase(@Valid @RequestBody CrearClaseDTO request) {
        var gymClass = classesInputPort.crearClase(
                request.nombre(),
                request.descripcion(),
                request.nivel(),
                request.duracionMinutos(),
                request.capacidadPorDefecto()
        );
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Edita una clase existente (solo MANAGER)")
    public ClassResponseDTO editarClase(@PathVariable Long id, @Valid @RequestBody EditarClaseDTO request) {
        var gymClass = classesInputPort.editarClase(
                id,
                request.nombre(),
                request.descripcion(),
                request.nivel(),
                request.duracionMinutos(),
                request.capacidadPorDefecto()
        );
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Elimina una clase (solo MANAGER)")
    public ResponseEntity<Void> eliminarClase(@PathVariable Long id) {
        classesInputPort.eliminarClase(id);
        return ResponseEntity.noContent().build();
    }
}
