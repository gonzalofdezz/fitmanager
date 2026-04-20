package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.LoginDTO;
import com.gonzalofdezz.fitmanager.application.dto.RegistroDTO;
import com.gonzalofdezz.fitmanager.application.dto.UsuarioResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.AutenticacionInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación")
public class AutenticacionController {

    private final AutenticacionInputPort autenticacionInputPort;

    public AutenticacionController(AutenticacionInputPort autenticacionInputPort) {
        this.autenticacionInputPort = autenticacionInputPort;
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra un nuevo usuario")
    public UsuarioResponseDTO registrar(@Valid @RequestBody RegistroDTO request) {
        try {
            var usuario = autenticacionInputPort.registrar(
                    request.nombre(),
                    request.email(),
                    request.contrasena()
            );
            return new UsuarioResponseDTO(
                    usuario.id(),
                    usuario.nombre(),
                    usuario.email(),
                    usuario.activo(),
                    usuario.fechaCreacion()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login de un usuario")
    public ResponseEntity<UsuarioResponseDTO> login(@Valid @RequestBody LoginDTO request) {
        var usuario = autenticacionInputPort.login(request.email(), request.contrasena());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = usuario.get();
        return ResponseEntity.ok(new UsuarioResponseDTO(
                user.id(),
                user.nombre(),
                user.email(),
                user.activo(),
                user.fechaCreacion()
        ));
    }

    @GetMapping("/usuarios/{email}")
    @Operation(summary = "Obtiene un usuario por su email")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorEmail(@PathVariable String email) {
        var usuario = autenticacionInputPort.obtenerPorEmail(email);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var user = usuario.get();
        return ResponseEntity.ok(new UsuarioResponseDTO(
                user.id(),
                user.nombre(),
                user.email(),
                user.activo(),
                user.fechaCreacion()
        ));
    }

    @GetMapping("/usuarios/{email}/existe")
    @Operation(summary = "Verifica si un email existe")
    public ResponseEntity<Boolean> emailExiste(@PathVariable String email) {
        return ResponseEntity.ok(autenticacionInputPort.emailExiste(email));
    }
}

