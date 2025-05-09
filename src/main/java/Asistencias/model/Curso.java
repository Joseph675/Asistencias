package Asistencias.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipoCurso"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CursoPresencial.class, name = "Presencial"),
    @JsonSubTypes.Type(value = CursoVirtual.class, name = "Virtual")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCurso", discriminatorType = DiscriminatorType.STRING)
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

    @Column(name = "cicloLectivo", nullable = false)
    private Integer cicloLectivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cuatrimestre cuatrimestre;

    @Column(name = "horasSemanales", nullable = false)
    private Integer horasSemanales;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    public Curso() {
    }

    public Curso(Integer id, Integer cursoPk, Integer materiaPk, Integer profesorId, Integer cicloLectivo,
            Cuatrimestre cuatrimestre, Integer horasSemanales, Estado estado) {
        this.id = id;
        this.cursoPk = cursoPk;
        this.materiaPk = materiaPk;
        this.profesorId = profesorId;
        this.cicloLectivo = cicloLectivo;
        this.cuatrimestre = cuatrimestre;
        this.horasSemanales = horasSemanales;
        this.estado = estado;
    }

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

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
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
        Primer,Segundo,Anual
    }

    public enum Estado {
        Planificado, En_Curso, Finalizado
    }
}