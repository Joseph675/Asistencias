package Asistencias.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Asistencias.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByEmail(String email);
}
