package Asistencias.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import Asistencias.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Materia findByNombreMateria(String nombreMateria);

    Optional<Materia> findById(Long id);

}

