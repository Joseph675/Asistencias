package Asistencias.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Usuario {

    private String especialidad;

    // Constructor sin argumentos
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(Long idUsuario, Long idUsuUni,String nombre, String email, String username, String password, String avatar, String especialidad, String facultad) {
        super(idUsuario,idUsuUni, nombre, email, username, password, avatar, facultad);
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
