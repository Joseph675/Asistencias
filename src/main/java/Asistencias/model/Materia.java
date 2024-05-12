package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    private String nombre_materia;
    

    // Constructor sin argumentos
    public Materia() {
    }

    // Getters y setters
    public Long getid_materia() {
        return id_materia;
    }

    public void setid_materia(Long id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    
}
