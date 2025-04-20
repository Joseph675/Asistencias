package Asistencias.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Virtual") // Valor del tipo en la columna "tipo"
public class CursoVirtual extends Curso {

    @Column(length = 100)
    private String plataforma;

    @Column(length = 255)
    private String enlaceAcceso;

    // Getters y Setters
    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getEnlaceAcceso() {
        return enlaceAcceso;
    }

    public void setEnlaceAcceso(String enlaceAcceso) {
        this.enlaceAcceso = enlaceAcceso;
    }
}