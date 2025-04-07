package Asistencias.controller;

import Asistencias.dto.UsuarioDTO;
import Asistencias.factory.UsuarioFactory;
import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;
import Asistencias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuariosDTO = usuarioService.obtenerTodos()
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getUsuarioId(),
                        usuario.getIdUsuUni(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getFacultad(),
                        usuario instanceof Estudiante ? ((Estudiante) usuario).getCarrera() : null,
                        usuario instanceof Profesor ? ((Profesor) usuario).getEspecialidad() : null,
                        usuario instanceof Administrativo ? ((Administrativo) usuario).getArea() : null,
                        usuario.getClass().getSimpleName().toLowerCase()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        return usuarioOpt.map(usuario -> ResponseEntity.ok(new UsuarioDTO(
                usuario.getUsuarioId(),
                usuario.getIdUsuUni(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFacultad(),
                usuario instanceof Estudiante ? ((Estudiante) usuario).getCarrera() : null,
                usuario instanceof Profesor ? ((Profesor) usuario).getEspecialidad() : null,
                usuario instanceof Administrativo ? ((Administrativo) usuario).getArea() : null,
                usuario.getClass().getSimpleName().toLowerCase()
        ))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = UsuarioFactory.crearUsuario(usuarioDTO);
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(
                    nuevoUsuario.getUsuarioId(),
                    nuevoUsuario.getIdUsuUni(),
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getEmail(),
                    nuevoUsuario.getFacultad(),
                    nuevoUsuario instanceof Estudiante ? ((Estudiante) nuevoUsuario).getCarrera() : null,
                    nuevoUsuario instanceof Profesor ? ((Profesor) nuevoUsuario).getEspecialidad() : null,
                    nuevoUsuario instanceof Administrativo ? ((Administrativo) nuevoUsuario).getArea() : null,
                    nuevoUsuario.getClass().getSimpleName().toLowerCase()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
            UsuarioDTO usuarioActualizadoDTO = new UsuarioDTO(
                    usuarioActualizado.getUsuarioId(),
                    usuarioActualizado.getIdUsuUni(),
                    usuarioActualizado.getNombre(),
                    usuarioActualizado.getEmail(),
                    usuarioActualizado.getFacultad(),
                    usuarioActualizado instanceof Estudiante ? ((Estudiante) usuarioActualizado).getCarrera() : null,
                    usuarioActualizado instanceof Profesor ? ((Profesor) usuarioActualizado).getEspecialidad() : null,
                    usuarioActualizado instanceof Administrativo ? ((Administrativo) usuarioActualizado).getArea() : null,
                    usuarioActualizado.getClass().getSimpleName().toLowerCase()
            );
            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
