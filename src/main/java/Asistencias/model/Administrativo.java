package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.time.LocalDateTime;


@Entity
@DiscriminatorValue("administrativo") // Valor del tipo en la columna "tipo"
public class Administrativo extends Usuario {

    @Column(nullable = true, length = 100)
    private String area;

    // Constructor sin argumentos
    public Administrativo() {}

    // Constructor con parámetros
    public Administrativo(Long usuarioId, Long idUsuUni, Integer cedula, String nombre,  String email, String passwordHash, String area, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Integer facultadId) {
        super(usuarioId, idUsuUni, cedula, nombre,  email, passwordHash, fechaNacimiento, activo, fechaCreacion, fechaActualizacion, facultadId);
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