package Asistencias.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencias", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "sesionId", "alumnoId" })
})
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer asistenciaId;

    @Column(nullable = false)
    private Integer sesionId;

    @Column(nullable = false)
    private String alumnoId;

    @Column(nullable = false)
    private Boolean presente = false;

    @Column(nullable = false)
    private Boolean justificada = false;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(nullable = false)
    private Integer registradoPor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    public Asistencia() {
    }

    public Asistencia(Integer asistenciaId, Integer sesionId, String alumnoId, Boolean presente, Boolean justificada,
            String observaciones, Integer registradoPor, LocalDateTime fechaRegistro) {
        this.asistenciaId = asistenciaId;
        this.sesionId = sesionId;
        this.alumnoId = alumnoId;
        this.presente = presente;
        this.justificada = justificada;
        this.observaciones = observaciones;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters
    public Integer getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(Integer asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public Integer getSesionId() {
        return sesionId;
    }

    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }

    public String getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(String alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Boolean getJustificada() {
        return justificada;
    }

    public void setJustificada(Boolean justificada) {
        this.justificada = justificada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Integer registradoPor) {
        this.registradoPor = registradoPor;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}