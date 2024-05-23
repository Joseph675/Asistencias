package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividades")
public class Actividad {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_actividad;

    private int id_profesor;
    private int id_materia;
    private String titulo;
    private String detalles_actividad;
    private String fecha_hora_creacion;

    // Constructor sin argumentos
    public Actividad() {
    }

    // Getters y setters
    public Long getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Long id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }


    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }


  
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalles_actividad() {
        return detalles_actividad;
    }

    public void setDetalles_actividad(String detalles_actividad) {
        this.detalles_actividad = detalles_actividad;
    }

    public String getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public void setFecha_hora_creacion(String fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    
    

  
}
