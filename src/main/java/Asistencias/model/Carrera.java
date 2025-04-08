package Asistencias.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long carreraPk;

    @Column(nullable = false, length = 100)
    private String nombre;
   
    @Column(nullable = false,  length = 100)
    private String codigo;

    @Column(nullable = false, unique = true)
    private Long facultadId;

    @Column(nullable = false, unique = true)
    private Long duracionanios;

    @Column(nullable = false,  length = 100)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activa = true;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;


    // Constructor sin argumentos
    public Carrera() {
    }

    // Constructor con parámetros
    public Carrera(Long id, Long carreraPk, String nombre, String codigo, Long facultadId, Long duracionanios, String descripcion, Boolean activa, LocalDateTime fechaCreacion) {
        this.id = id;
        this.carreraPk = carreraPk;
        this.nombre = nombre;
        this.codigo = codigo;
        this.facultadId = facultadId;
        this.duracionanios = duracionanios;
        this.descripcion = descripcion;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarreraPk() {
        return carreraPk;
    }

    public void setCarreraPk(Long carreraPk) {
        this.carreraPk = carreraPk;
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

    public Long getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Long facultadId) {
        this.facultadId = facultadId;
    }

    public Long getDuracionanios() {
        return duracionanios;
    }

    public void setDuracionanios(Long duracionanios) {
        this.duracionanios = duracionanios;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
    
}
