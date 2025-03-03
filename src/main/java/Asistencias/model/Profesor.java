package Asistencias.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario {

    private String especialidad;

    // Constructor sin argumentos
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(String nombre, String email, String username, String password, String avatar, String especialidad) {
        super(nombre, email, username, password, avatar);
        this.especialidad = especialidad;
    }

    // Getters y Setters
    

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
}
