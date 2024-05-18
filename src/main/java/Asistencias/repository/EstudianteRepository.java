package Asistencias.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Asistencias.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM estudiantes WHERE id_estudiante = :idEstudiante", nativeQuery = true)
    List<Estudiante> findByIdEstudiante(Long idEstudiante);
}
