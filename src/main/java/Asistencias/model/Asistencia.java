package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asistencias")
public class Asistencia {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asistencia;

    private int id_estudiante;
    private int id_materia;
    private String fecha;
    private String asistio;
    

    // Constructor sin argumentos
    public Asistencia() {
    }

    // Getters y setters
    public Long getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(Long id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }
  
   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsistio() {
        return asistio;
    }

    public void setAsistio(String asistio) {
        this.asistio = asistio;
    }

    
}


