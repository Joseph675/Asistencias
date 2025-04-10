package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.time.LocalDateTime;


@Entity
@DiscriminatorValue("alumno") // Valor del tipo en la columna "tipo"
public class Estudiante extends Usuario {

    @Column(nullable = true, length = 100)
    private String carrera;

    // Constructor sin argumentos
    public Estudiante() {}

    // Constructor con parámetros
    public Estudiante(Long usuarioId, Long idUsuUni, Integer cedula, String nombre,  String email, String passwordHash, String carrera, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Integer facultadId) {
        super(usuarioId, idUsuUni, cedula, nombre,  email, passwordHash, fechaNacimiento, activo, fechaCreacion, fechaActualizacion, facultadId);
        this.carrera = carrera;
    }

    // Getters y Setters
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}