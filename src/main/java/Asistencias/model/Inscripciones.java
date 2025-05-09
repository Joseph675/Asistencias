package Asistencias.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripciones")
public class Inscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscripcionId;

    @Column(nullable = false, unique = true)
    private Integer cursoPk;

    @Column(nullable = false, unique = true)
    private Integer alumnoId;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    public Inscripciones() {
    }

    public Inscripciones(Long inscripcionId, Integer cursoPk, Integer alumnoId, LocalDateTime fechaInscripcion,
            Estado estado) {
        this.inscripcionId = inscripcionId;
        this.cursoPk = cursoPk;
        this.alumnoId = alumnoId;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    public Long getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Long inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Integer getCursoPk() {
        return cursoPk;
    }

    public void setCursoPk(Integer cursoPk) {
        this.cursoPk = cursoPk;
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public enum Estado {
        Activo, Inactivo
    }

}
