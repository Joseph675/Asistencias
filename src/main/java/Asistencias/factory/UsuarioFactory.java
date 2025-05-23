package Asistencias.factory;

import Asistencias.dto.UsuarioDTO;
import Asistencias.model.Administrador;
import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase de fábrica para crear instancias de Usuario.
 * Esta clase utiliza el patrón de diseño Factory para crear diferentes tipos de
 * usuarios (Estudiante, Profesor, Administrativo, Administrador) a partir de un
 * DTO.
 */
public class UsuarioFactory {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Crear una instancia de Usuario basada en el tipo de usuario especificado en
     * el DTO.
     * 
     * @param usuarioDTO DTO con los datos del usuario.
     * @return Instancia de Usuario (Estudiante, Profesor, Administrativo,
     *         Administrador).
     */
    public static Usuario crearUsuario(UsuarioDTO usuarioDTO, String password) {
        // Generar el hash de la contraseña
        String passwordHash = passwordEncoder.encode(password);

        switch (usuarioDTO.getTipo()) {
            case "Alumno": // Caso para Estudiante
                return new Estudiante(
                        usuarioDTO.getIdUsuario(),
                        usuarioDTO.getIdUsuUni(),
                        usuarioDTO.getCedula(),
                        usuarioDTO.getNombre(),
                        usuarioDTO.getEmail(),
                        passwordHash,
                        usuarioDTO.getCarrera(),
                        usuarioDTO.getFechaNacimiento(), // Asignar fecha de nacimiento
                        usuarioDTO.getActivo(), // Asignar estado activo
                        null, // Fecha de creación (se genera automáticamente)
                        null, // Fecha de actualización (se genera automáticamente)
                        usuarioDTO.getFacultadId(), // Asignar facultadId
                        usuarioDTO.getUid() 



                );
            case "Profesor": // Caso para Profesor
                return new Profesor(
                        usuarioDTO.getIdUsuario(),
                        usuarioDTO.getIdUsuUni(),
                        usuarioDTO.getCedula(),
                        usuarioDTO.getNombre(),
                        usuarioDTO.getEmail(),
                        passwordHash,
                        usuarioDTO.getEspecialidad(), // Campo específico de Profesor
                        usuarioDTO.getFechaNacimiento(), // Asignar fecha de nacimiento
                        usuarioDTO.getActivo(), // Asignar estado activo
                        null, // Fecha de creación
                        null, // Fecha de actualización
                        usuarioDTO.getFacultadId(), // Asignar facultadId
                        usuarioDTO.getUid() 
                );
            case "Administrativo": // Caso para Administrativo
                return new Administrativo(
                        usuarioDTO.getIdUsuario(),
                        usuarioDTO.getIdUsuUni(),
                        usuarioDTO.getCedula(),
                        usuarioDTO.getNombre(),
                        usuarioDTO.getEmail(),
                        passwordHash,
                        usuarioDTO.getArea(), // Campo específico de Administrativo
                        usuarioDTO.getFechaNacimiento(), // Asignar fecha de nacimiento
                        usuarioDTO.getActivo(), // Asignar estado activo
                        null, // Fecha de creación
                        null, // Fecha de actualización
                        usuarioDTO.getFacultadId(), // Asignar facultadId
                        usuarioDTO.getUid() 
                );
            case "Admin": // Caso para Administrador
                return new Administrador(
                        usuarioDTO.getIdUsuario(),
                        usuarioDTO.getIdUsuUni(),
                        usuarioDTO.getCedula(),
                        usuarioDTO.getNombre(),
                        usuarioDTO.getEmail(),
                        passwordHash, // Asignar passwordHash
                        usuarioDTO.getFechaNacimiento(), // Asignar fecha de nacimiento
                        usuarioDTO.getActivo(), // Asignar estado activo
                        null, // Fecha de creación
                        null, // Fecha de actualización
                        usuarioDTO.getFacultadId(), // Asignar facultadId
                        usuarioDTO.getUid() 
                );
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + usuarioDTO.getTipo());
        }
    }
}