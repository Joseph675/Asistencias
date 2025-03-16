package Asistencias.factory;

import Asistencias.dto.UsuarioDTO;
import Asistencias.model.Administrador;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;

public class UsuarioFactory {

    public static Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        switch (usuarioDTO.getTipoUsuario()) {
            case "ESTUDIANTE":
                return new Estudiante(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getUsername(),
                    null, // Password should be set separately
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getFacultad(),
                    usuarioDTO.getCarrera() // Usar el campo carrera
                );
            case "PROFESOR":
                return new Profesor(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getUsername(),
                    null, // Password should be set separately
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getFacultad(),
                    usuarioDTO.getEspecialidad() // Usar el campo especialidad
                );
            case "ADMINISTRADOR":
                return new Administrador(
                    usuarioDTO.getIdUsuario(),
                    usuarioDTO.getIdUsuUni(),
                    usuarioDTO.getNombre(),
                    usuarioDTO.getEmail(),
                    usuarioDTO.getUsername(),
                    null, // Password should be set separately
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getFacultad(),
                    usuarioDTO.getArea() // Usar el campo area
                );
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido");
        }
    }
}