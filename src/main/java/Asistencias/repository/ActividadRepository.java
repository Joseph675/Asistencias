package Asistencias.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Asistencias.model.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    Actividad findByTitulo(String titulo);

    
}
