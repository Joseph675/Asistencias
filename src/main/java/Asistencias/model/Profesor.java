package Asistencias.model;

import jakarta.persistence.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario {

    private String especialidad;

    // Constructor sin argumentos
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(int idUsuUni,String nombre, String email, String username, String password, String avatar, String especialidad, String facultad) {
        super(idUsuUni, nombre, email, username, password, avatar, facultad);
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
