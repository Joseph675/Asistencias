package Asistencias.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Carrera_MateriaId {
    private Integer carreraPk;
    private Integer materiaPk;

    // Constructor vacío (requerido por JPA)
    public Carrera_MateriaId() {
    }

    // Constructor con parámetros
    public Carrera_MateriaId(Integer carreraPk, Integer materiaPk) {
        this.carreraPk = carreraPk;
        this.materiaPk = materiaPk;
    }

    // Getters y Setters
    public Integer getCarreraPk() {
        return carreraPk;
    }

    public void setCarreraPk(Integer carreraPk) {
        this.carreraPk = carreraPk;
    }

    public Integer getMateriaPk() {
        return materiaPk;
    }

    public void setMateriaPk(Integer materiaPk) {
        this.materiaPk = materiaPk;
    }

    // Sobrescribir equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son el mismo objeto, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Si no son del mismo tipo, no son iguales
        Carrera_MateriaId that = (Carrera_MateriaId) o;
        return Objects.equals(carreraPk, that.carreraPk) &&
               Objects.equals(materiaPk, that.materiaPk); // Comparar los valores de carreraPk y materiaPk
    }

    // Sobrescribir hashCode
    @Override
    public int hashCode() {
        return Objects.hash(carreraPk, materiaPk); // Generar un hash basado en carreraPk y materiaPk
    }
}
