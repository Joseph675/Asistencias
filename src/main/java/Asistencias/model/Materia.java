package Asistencias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "materiaPk", unique = true, nullable = false)
    private Integer materiaPk;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false)
    private Integer creditos;

    @Column(name = "carreraPk", nullable = false)
    private Integer carreraPk;

    @Column(name = "cupoMaximo")
    private Integer cupoMaximo;

    @Column(name = "anioCursada", nullable = false)
    private Integer anioCursada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cuatrimestre cuatrimestre;

    @Column(name = "horasSemanales", nullable = false)
    private Integer horasSemanales;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Boolean activa = true;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMateriaPk() {
        return materiaPk;
    }

    public void setMateriaPk(Integer materiaPk) {
        this.materiaPk = materiaPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getCarreraPk() {
        return carreraPk;
    }

    public void setCarreraPk(Integer carreraPk) {
        this.carreraPk = carreraPk;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Integer getAnioCursada() {
        return anioCursada;
    }

    public void setAnioCursada(Integer anioCursada) {
        this.anioCursada = anioCursada;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public enum Cuatrimestre {
        PRIMERO, SEGUNDO, ANUAL
    }
}