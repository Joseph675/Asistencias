package Asistencias.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    private String area;

    public Administrador() {}

    public Administrador(String nombre, String email, String username, String password, String avatar, String area) {
        super(nombre, email, username, password, avatar);
        this.area = area;
    }

    // Getters y Setters
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
}
