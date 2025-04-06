package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DiscriminatorValue("profesor") // Valor del tipo en la columna "tipo"
public class Profesor extends Usuario {

    @Column(nullable = true, length = 100)
    private String especialidad;

    // Constructor sin argumentos
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(Long usuarioId, Long idUsuUni, Integer cedula, String nombre, String apellido, String email, String passwordHash, String especialidad, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        super(usuarioId, idUsuUni, cedula, nombre, apellido, email, passwordHash, null, especialidad, null, fechaNacimiento, activo, fechaCreacion, fechaActualizacion);
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
