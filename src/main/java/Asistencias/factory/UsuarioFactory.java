package Asistencias.factory;

import Asistencias.dto.UsuarioDTO;
import Asistencias.model.Administrador;
import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;

public class UsuarioFactory {

    /**
     * Crear una instancia de Usuario basada en el tipo de usuario especificado en el DTO.
     * @param usuarioDTO DTO con los datos del usuario.
     * @return Instancia de Usuario (Estudiante, Profesor, Administrativo, Administrador).
     */
    public static Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        switch (usuarioDTO.getTipoUsuario().toLowerCase()) {
            case "alumno": // Caso para Estudiante
                return new Estudiante(
                    usuarioDTO.getIdUsuario(), // Long usuarioId
                    usuarioDTO.getIdUsuUni(), // Long idUsuUni
                    null, // Integer cedula (puedes ajustarlo si es necesario)
                    usuarioDTO.getNombre(), // String nombre
                    usuarioDTO.getEmail(), // String email
                    null, // String passwordHash (debe manejarse aparte)
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
                    null, // Cedula
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    null, // PasswordHash
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
                    null, // Cedula
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    null, // PasswordHash
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
                    null, // Cedula
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
                throw new IllegalArgumentException("Tipo de usuario no válido: " + usuarioDTO.getTipoUsuario());
        }
    }
}