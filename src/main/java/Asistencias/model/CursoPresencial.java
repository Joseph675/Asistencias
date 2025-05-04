package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Presencial") // Valor del tipo en la columna "tipo"
public class CursoPresencial extends Curso {

    @Column(length = 50)
    private String aula;

    private Integer capacidad;

    // Getters y Setters
    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}