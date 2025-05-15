package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DiscriminatorValue("Profesor") // Valor del tipo en la columna "tipo"
public class Profesor extends Usuario {

    @Column(nullable = true, length = 100)
    private String especialidad;

    // Constructor sin argumentos
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(Long usuarioId, Long idUsuUni, Integer cedula, String nombre,  String email, String passwordHash, String especialidad, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Integer facultadId, String uid) {
        super(usuarioId, idUsuUni, cedula, nombre,  email, passwordHash,  fechaNacimiento, activo, fechaCreacion, fechaActualizacion, facultadId, uid);
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
