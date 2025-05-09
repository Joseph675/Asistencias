package Asistencias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrera_materia")
public class Carrera_Materia {

    @EmbeddedId
    private Carrera_MateriaId id;

    @Column(nullable = false)
    private Integer anioCursada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cuatrimestre cuatrimestre;

    public Carrera_Materia() {
    }

    public Carrera_Materia(Carrera_MateriaId id, Integer anioCursada, Cuatrimestre cuatrimestre) {
        this.id = id;
        this.anioCursada = anioCursada;
        this.cuatrimestre = cuatrimestre;
    }

    public Carrera_MateriaId getId() {
        return id;
    }

    public void setId(Carrera_MateriaId id) {
        this.id = id;
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

    public enum Cuatrimestre {
        Primer, Segundo, Anual
    }

    
}