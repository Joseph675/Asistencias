package Asistencias.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "cursos")
public abstract class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cursoPk", unique = true, nullable = false)
    private Integer cursoPk;

    @Column(name = "materiaPk", nullable = false)
    private Integer materiaPk;

    @Column(name = "profesorId", nullable = false)
    private Integer profesorId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horario;

    @Column(name = "cicloLectivo", nullable = false)
    private Integer cicloLectivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cuatrimestre cuatrimestre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCursoPk() {
        return cursoPk;
    }

    public void setCursoPk(Integer cursoPk) {
        this.cursoPk = cursoPk;
    }

    public Integer getMateriaPk() {
        return materiaPk;
    }

    public void setMateriaPk(Integer materiaPk) {
        this.materiaPk = materiaPk;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getCicloLectivo() {
        return cicloLectivo;
    }

    public void setCicloLectivo(Integer cicloLectivo) {
        this.cicloLectivo = cicloLectivo;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public enum Cuatrimestre {
        PRIMERO, SEGUNDO, ANUAL
    }

    public enum Estado {
        PLANIFICADO, EN_CURSO, FINALIZADO
    }
}