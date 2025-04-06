package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("admin") // Valor del tipo en la columna "tipo"
public class Administrador extends Usuario {

    @Column(nullable = true, length = 100)
    private String area;

    // Constructor sin argumentos
    public Administrador() {}

    // Constructor con parámetros
    public Administrador(Long usuarioId, Long idUsuUni, Integer cedula, String nombre, String apellido, String email, String passwordHash, String area, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        super(usuarioId, idUsuUni, cedula, nombre, apellido, email, passwordHash, null, null, area, fechaNacimiento, activo, fechaCreacion, fechaActualizacion);
        this.area = area;
    }

    // Getters y Setters
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}