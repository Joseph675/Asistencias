package Asistencias.controller;

import Asistencias.dto.UsuarioDTO;
import Asistencias.factory.UsuarioFactory;
import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;
import Asistencias.model.UsuarioRequest;
import Asistencias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
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
                        usuario.getCedula(),
                        usuario.getNombre(),
                        usuario.getEmail(),
                        usuario.getFacultadId(),
                        usuario instanceof Estudiante ? ((Estudiante) usuario).getCarrera() : null,
                        usuario instanceof Profesor ? ((Profesor) usuario).getEspecialidad() : null,
                        usuario instanceof Administrativo ? ((Administrativo) usuario).getArea() : null,
                        usuario.getFechaNacimiento(),
                        usuario.getActivo(),
                        usuario.getTipo(),
                        usuario.getUid()))
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
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getFacultadId(),
                usuario instanceof Estudiante ? ((Estudiante) usuario).getCarrera() : null,
                usuario instanceof Profesor ? ((Profesor) usuario).getEspecialidad() : null,
                usuario instanceof Administrativo ? ((Administrativo) usuario).getArea() : null,
                usuario.getFechaNacimiento(),
                usuario.getActivo(),
                usuario.getTipo(),
                usuario.getUid()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/profesores")
    public ResponseEntity<List<UsuarioDTO>> getProfesores() {
        List<UsuarioDTO> profesoresDTO = usuarioService.obtenerProfesores()
                .stream()
                .map(profesor -> new UsuarioDTO(
                        profesor.getUsuarioId(),
                        profesor.getIdUsuUni(),
                        profesor.getCedula(),
                        profesor.getNombre(),
                        profesor.getEmail(),
                        profesor.getFacultadId(),
                        null, // Los profesores no tienen carrera
                        profesor instanceof Profesor ? ((Profesor) profesor).getEspecialidad() : null,
                        null, // Los profesores no tienen área
                        profesor.getFechaNacimiento(),
                        profesor.getActivo(),
                        profesor.getTipo(),
                        profesor.getUid()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profesoresDTO);
    }

    @GetMapping("/alumno")
    public ResponseEntity<List<UsuarioDTO>> getAlumnos() {
        List<UsuarioDTO> alumnosDTO = usuarioService.obtenerAlumnos()
                .stream()
                .map(alumno -> new UsuarioDTO(
                        alumno.getUsuarioId(),
                        alumno.getIdUsuUni(),
                        alumno.getCedula(),
                        alumno.getNombre(),
                        alumno.getEmail(),
                        alumno.getFacultadId(),
                        alumno instanceof Estudiante ? ((Estudiante) alumno).getCarrera() : null,
                        null, // Los alumnos no tienen especialidad
                        null, // Los profesores no tienen área
                        alumno.getFechaNacimiento(),
                        alumno.getActivo(),
                        alumno.getTipo(),
                        alumno.getUid()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(alumnosDTO);
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            UsuarioDTO usuarioDTO = usuarioRequest.getUsuarioDTO();
            String password = usuarioRequest.getPassword();

            // Crear el usuario con el hash de la contraseña
            Usuario usuario = UsuarioFactory.crearUsuario(usuarioDTO, password);
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);

            // Crear el DTO para la respuesta
            UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(
                    nuevoUsuario.getUsuarioId(),
                    nuevoUsuario.getIdUsuUni(),
                    nuevoUsuario.getCedula(),
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getEmail(),
                    nuevoUsuario.getFacultadId(),
                    nuevoUsuario instanceof Estudiante ? ((Estudiante) nuevoUsuario).getCarrera() : null,
                    nuevoUsuario instanceof Profesor ? ((Profesor) nuevoUsuario).getEspecialidad() : null,
                    nuevoUsuario instanceof Administrativo ? ((Administrativo) nuevoUsuario).getArea() : null,
                    nuevoUsuario.getFechaNacimiento(),
                    nuevoUsuario.getActivo(),
                    nuevoUsuario.getTipo(),
                    nuevoUsuario.getUid());

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioDTO);
        } catch (DataIntegrityViolationException ex) {
            // Revisa si el mensaje contiene 'unique_uid'
            if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("unique_uid")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El UID ya está registrado en el sistema.");
            }

            if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("unique_idUsuUni")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El ID Universitario ya está registrado en el sistema.");
            }

            if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("unique_cedula")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("La Cedula ya está registrado en el sistema.");
            }

            if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("unique_email")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El Email ya está registrado en el sistema.");
            }
            // Puedes hacer lo mismo para email, cedula, etc, si lo deseas.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error de integridad: " + ex.getRootCause().getMessage());
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
                    usuarioActualizado.getCedula(),
                    usuarioActualizado.getNombre(),
                    usuarioActualizado.getEmail(),
                    usuarioActualizado.getFacultadId(),
                    usuarioActualizado instanceof Estudiante ? ((Estudiante) usuarioActualizado).getCarrera() : null,
                    usuarioActualizado instanceof Profesor ? ((Profesor) usuarioActualizado).getEspecialidad() : null,
                    usuarioActualizado instanceof Administrativo ? ((Administrativo) usuarioActualizado).getArea()
                            : null,
                    usuarioActualizado.getFechaNacimiento(),
                    usuarioActualizado.getActivo(),
                    usuarioActualizado.getTipo(),
                    usuarioActualizado.getUid());

            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long usuarioId) {
        usuarioService.eliminar(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
