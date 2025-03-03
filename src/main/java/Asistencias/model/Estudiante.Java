package Asistencias.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario {

    private int IdEstu;
    private String matricula;
    private String carrera;

    // Constructor sin argumentos
    public Estudiante() {
    }

    // Constructor con parámetros
    public Estudiante(int IdEstu, String nombre, String email, String username, String password, String avatar, String matricula, String carrera) {
        super(nombre, email, username, password, avatar);
        this.IdEstu = IdEstu;
        this.matricula = matricula;
        this.carrera = carrera;
    }

    // Getters y Setters
    public int getIdEstu() {
        return IdEstu;
    }
    public void setIdEstu(int IdEstu) {
        this.IdEstu = IdEstu;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
