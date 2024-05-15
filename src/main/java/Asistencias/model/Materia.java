package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    @Column(name = "nombre_materia")
    private String nombreMateria;

    // Constructor sin argumentos
    public Materia() {
    }

    // Getters y setters
    public Long getIdMateria() {
        return id_materia;
    }

    public void setIdMateria(Long id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
}