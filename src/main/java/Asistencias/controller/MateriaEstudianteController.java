package Asistencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.MateriaEstudiante;
import Asistencias.repository.MateriaEstudianteRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/materiaestudiante")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaEstudianteController {

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<MateriaEstudiante> getMateriaEstudiante() {
        return materiaestudianteRepository.findAll();
    }

    @GetMapping("/porEstudiante/{id_estudiante}")
    public List<MateriaEstudiante> getMateriaEstudiantePorEstudiante(@PathVariable Long id_estudiante) {
        return materiaestudianteRepository.findByIdEstudiante(id_estudiante);
    }


    @GetMapping("/porMateria/{id_materia}")
    public List<MateriaEstudiante> getMateriaEstudiantePorMateria(@PathVariable Long id_materia) {
        return materiaestudianteRepository.findByIdMateria(id_materia);
    }

    @PostMapping
    public ResponseEntity<?> createMateriaEstudiante(@RequestBody MateriaEstudiante materiaestudiante) {
        List<MateriaEstudiante> existingEntries = materiaestudianteRepository.findByIdEstudianteAndIdMateriaAndHoraAndDias(
                materiaestudiante.getId_estudiante(),
                materiaestudiante.getId_materia(),
                materiaestudiante.getHora(),
                materiaestudiante.getDias()
        );

        if (!existingEntries.isEmpty()) {
            return new ResponseEntity<>("Ya existe un registro con los mismos datos.", HttpStatus.CONFLICT);
        }

        materiaestudianteRepository.save(materiaestudiante);
        return new ResponseEntity<>(materiaestudiante, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMateriaEstudiante(@PathVariable Long id) {
        materiaestudianteRepository.deleteById(id);
    }

    @PutMapping("/{id}")
public ResponseEntity<?> updateMateriaEstudiante(@PathVariable Long id, @RequestBody MateriaEstudiante materiaestudianteDetails) {
    MateriaEstudiante materiaestudiante = materiaestudianteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("MateriaEstudiante no encontrado con id :" + id));

   // Verifica si ya existe una fila con los mismos valores
List<MateriaEstudiante> existingRows = materiaestudianteRepository.findByIdEstudianteAndIdMateriaAndHoraAndDias(
    materiaestudianteDetails.getId_estudiante(),
    materiaestudianteDetails.getId_materia(),
    materiaestudianteDetails.getHora(),
    materiaestudianteDetails.getDias()
);
    if (!existingRows.isEmpty()) {
        // Si existe, devuelve un mensaje de error
        return ResponseEntity.badRequest().body("Ya existe una fila con los mismos valores");
    }

    // Actualiza las propiedades de materiaestudiante con los valores de materiaestudianteDetails
    materiaestudiante.setDias(materiaestudianteDetails.getDias());
    materiaestudiante.setHora(materiaestudianteDetails.getHora());
    // Asegúrate de actualizar todas las demás propiedades que quieras cambiar

    MateriaEstudiante updateMateriaEstudiante = materiaestudianteRepository.save(materiaestudiante);
    return ResponseEntity.ok(updateMateriaEstudiante);
}

    

}
