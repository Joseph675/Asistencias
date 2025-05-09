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
 
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Boolean activa = true;


    public Materia() {
    }

    public Materia(Integer id, Integer materiaPk, String nombre, String codigo, Integer creditos, String descripcion,
            Boolean activa) {
        this.id = id;
        this.materiaPk = materiaPk;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.descripcion = descripcion;
        this.activa = activa;
    }

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

    
}