package Asistencias.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Asistencias.model.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    
}
