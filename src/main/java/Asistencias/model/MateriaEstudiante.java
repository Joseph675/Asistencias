package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiantes_materias")
@IdClass(MateriaEstudianteId.class)
public class MateriaEstudiante {

    @Id
    private int id_estudiante;

    @Id
    private int id_materia;

    private String hora;
    private String dias;

    // Constructor sin argumentos
    public MateriaEstudiante() {
    }

    // Getters y setters
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
