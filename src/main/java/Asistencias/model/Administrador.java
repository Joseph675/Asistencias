package Asistencias.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("Admin") // Valor del tipo en la columna "tipo"
public class Administrador extends Usuario {


    // Constructor sin argumentos
    public Administrador() {}

    // Constructor con parámetros
    public Administrador(Long usuarioId, Long idUsuUni, Integer cedula, String nombre,  String email, String passwordHash, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Integer facultadId, String uid) {
        super(usuarioId, idUsuUni, cedula, nombre, email, passwordHash, fechaNacimiento, activo, fechaCreacion, fechaActualizacion, facultadId, uid);
    }

    
}