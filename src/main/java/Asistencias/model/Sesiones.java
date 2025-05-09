package Asistencias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sesiones")
public class Sesiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sesionId;

    @Column(nullable = false)
    private Integer cursoPk;

    @Column(nullable = false, length = 10)
    private String dia_semana;

    @Column(nullable = false)
    private String horaInicio;

    @Column(nullable = false)
    private String horaFin;

    @Column(length = 255)
    private String tema;

    // Constructor vacío
    public Sesiones() {
    }

    // Constructor con todos los campos
    public Sesiones(Integer sesionId, Integer cursoPk, String dia_semana, String horaInicio, String horaFin, String tema) {
        this.sesionId = sesionId;
        this.cursoPk = cursoPk;
        this.dia_semana = dia_semana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tema = tema;
    }

    // Getters y Setters
    public Integer getSesionId() {
        return sesionId;
    }

    public void setSesionId(Integer sesionId) {
        this.sesionId = sesionId;
    }

    public Integer getCursoPk() {
        return cursoPk;
    }

    public void setCursoPk(Integer cursoPk) {
        this.cursoPk = cursoPk;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}