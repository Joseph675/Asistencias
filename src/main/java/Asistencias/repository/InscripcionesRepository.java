package Asistencias.repository;

import Asistencias.model.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionesRepository extends JpaRepository<Inscripciones, Long> {
    // Métodos personalizados si son necesarios
    
}
