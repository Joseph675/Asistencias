package Asistencias.repository;

import Asistencias.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    // Buscar asistencias por sesión
    List<Asistencia> findBySesionId(Integer sesionId);

    // Buscar asistencias por alumno
    List<Asistencia> findByAlumnoId(String alumnoId);

    // Buscar asistencias por sesión y alumno
    Asistencia findBySesionIdAndAlumnoId(Integer sesionId, String alumnoId);

    boolean existsBySesionIdAndAlumnoId(Integer sesionId, String alumnoId);
}