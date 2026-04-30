package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.LoginDTO;
import com.gonzalofdezz.fitmanager.application.dto.LoginResponseDTO;
import com.gonzalofdezz.fitmanager.application.dto.RegistroDTO;
import com.gonzalofdezz.fitmanager.application.dto.UsuarioResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.AutenticacionInputPort;
import com.gonzalofdezz.fitmanager.config.security.JwtService;
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
    private final JwtService jwtService;

    public AutenticacionController(AutenticacionInputPort autenticacionInputPort, JwtService jwtService) {
        this.autenticacionInputPort = autenticacionInputPort;
        this.jwtService = jwtService;
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
                    usuario.fechaCreacion(),
                    usuario.rol() != null ? usuario.rol().name() : "USER"
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login de un usuario")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO request) {
        var usuario = autenticacionInputPort.login(request.email(), request.contrasena());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = usuario.get();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(
                user.id(),
                user.nombre(),
                user.email(),
                user.activo(),
                user.fechaCreacion(),
                user.rol() != null ? user.rol().name() : "USER",
                token
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
                user.fechaCreacion(),
                user.rol() != null ? user.rol().name() : "USER"
        ));
    }

    @GetMapping("/usuarios/{email}/existe")
    @Operation(summary = "Verifica si un email existe")
    public ResponseEntity<Boolean> emailExiste(@PathVariable String email) {
        return ResponseEntity.ok(autenticacionInputPort.emailExiste(email));
    }
}

