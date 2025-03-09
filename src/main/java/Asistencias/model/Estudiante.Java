package Asistencias.model;

import jakarta.persistence.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{

    private String carrera;

    // Constructor sin argumentos
    public Estudiante() {
    }

    // Constructor con parámetros
    public Estudiante(int idUsuUni, String nombre, String email, String username, String password, String avatar, String carrera,String facultad) {
        super(idUsuUni, nombre, email, username, password, avatar,facultad);
        this.carrera = carrera;
    }

    // Getters y Setters

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
} 