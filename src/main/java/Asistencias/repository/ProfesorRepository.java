package Asistencias.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Asistencias.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByEmail(String email);
}
