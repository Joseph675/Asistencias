package Asistencias.repository;

import Asistencias.model.Carrera_Materia;
import Asistencias.model.Carrera_MateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Carrera_MateriaRepository extends JpaRepository<Carrera_Materia, Carrera_MateriaId> {
    // Métodos personalizados si son necesarios
}