package Asistencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import Asistencias.model.MateriaEstudiante;

public interface MateriaEstudianteRepository extends JpaRepository<MateriaEstudiante, Long> {

    @Modifying
    @Transactional
    @Query("delete from MateriaEstudiante me where me.id_estudiante = ?1")
    void deleteByIdEstudiante(Long id);

    @Modifying
    @Query("delete from MateriaEstudiante em where em.id_materia = :idMateria")
    void deleteByIdMateria(@Param("idMateria") Long idMateria);

    @Query(value = "SELECT * FROM estudiantes_materias WHERE id_estudiante = :idEstudiante AND id_materia = :idMateria AND hora = :hora AND dias = :dias", nativeQuery = true)
    List<MateriaEstudiante> findByIdEstudianteAndIdMateriaAndHoraAndDias(int idEstudiante, int idMateria, String hora, String dias);

    @Query(value = "SELECT * FROM estudiantes_materias WHERE id_estudiante = :idEstudiante", nativeQuery = true)
    List<MateriaEstudiante> findByIdEstudiante(Long idEstudiante);

}
