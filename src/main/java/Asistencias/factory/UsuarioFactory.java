package Asistencias.factory;

import Asistencias.dto.UsuarioDTO;
import Asistencias.model.Administrador;
import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UsuarioFactory {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Crear una instancia de Usuario basada en el tipo de usuario especificado en el DTO.
     * @param usuarioDTO DTO con los datos del usuario.
     * @return Instancia de Usuario (Estudiante, Profesor, Administrativo, Administrador).
     */
    public static Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        String passwordHash = passwordEncoder.encode(usuarioDTO.getPassword());
        switch (usuarioDTO.getTipo().toLowerCase()) {
            case "alumno": // Caso para Estudiante
                return new Estudiante(
                    usuarioDTO.getIdUsuario(), // Long usuarioId
                    usuarioDTO.getIdUsuUni(), // Long idUsuUni
                    usuarioDTO.getCedula(), // Long idUsuUni
                    usuarioDTO.getNombre(), // String nombre
                    usuarioDTO.getEmail(), // String email
                    passwordHash, 
                    usuarioDTO.getCarrera(), // String carrera
                    null, // Date fechaNacimiento (si aplica)
                    true, // Boolean activo por defecto
                    null, // LocalDateTime fechaCreacion (se genera automáticamente)
                    null, // LocalDateTime fechaActualizacion (se genera automáticamente)
                    usuarioDTO.getFacultad() // String facultad
    );
            case "profesor": // Caso para Profesor
                return new Profesor(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getCedula(), // Long idUsuUni

                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    passwordHash, 
                    usuarioDTO.getEspecialidad(), // Campo específico de Profesor
                    null, // Fecha de nacimiento
                    true, // Activo
                    null, // Fecha de creación
                    null, // Fecha de actualización
                    usuarioDTO.getFacultad()
                );
            case "administrativo": // Caso para Administrativo
                return new Administrativo(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getCedula(), // Long idUsuUni
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    passwordHash, 
                    usuarioDTO.getArea(), // Campo específico de Administrativo
                    null, // Fecha de nacimiento
                    true, // Activo
                    null, // Fecha de creación
                    null, // Fecha de actualización
                    usuarioDTO.getFacultad()
                );
            case "admin": // Caso para Administrador
                return new Administrador(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getCedula(), // Long idUsuUni

                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    null, // PasswordHash
                    null, // Fecha de nacimiento
                    true, // Activo
                    null, // Fecha de creación
                    null, // Fecha de actualización
                    usuarioDTO.getFacultad()
                );
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + usuarioDTO.getTipo());
        }
    }
}