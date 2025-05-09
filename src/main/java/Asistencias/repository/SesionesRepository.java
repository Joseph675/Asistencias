package Asistencias.repository;

import Asistencias.model.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Integer> {

    @Query(value = "SELECT cursoPk, " +
                   "GROUP_CONCAT(dia_semana ORDER BY dia_semana ASC) AS dias, " +
                   "GROUP_CONCAT(CONCAT(horaInicio, ' - ', horaFin) ORDER BY dia_semana ASC) AS horarios " +
                   "FROM sesiones " +
                   "GROUP BY cursoPk", 
           nativeQuery = true)
    List<Object[]> findSesionesGroupedByCurso();
}