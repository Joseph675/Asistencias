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

import Asistencias.model.MateriaProfesor;
import Asistencias.repository.MateriaProfesorRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/materiaprofesor")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaProfesorController {

    @Autowired
    private MateriaProfesorRepository materiaprofesorRepository;

    @GetMapping
    public List<MateriaProfesor> MateriaProfesor() {
        return materiaprofesorRepository.findAll();
    }

    @GetMapping("/porEstudiante/{id_profesor}")
    public List<MateriaProfesor> getMateriaEstudiantePorProfesor(@PathVariable Long id_profesor) {
        return materiaprofesorRepository.findByIdProfesor(id_profesor);
    }

    @PostMapping
    public ResponseEntity<?> createMateriaProfesor(@RequestBody MateriaProfesor materiaprofesor) {
        List<MateriaProfesor> existingEntries = materiaprofesorRepository.findByIdProfesorAndIdMateriaAndHoraAndDias(
                materiaprofesor.getId_profesor(),
                materiaprofesor.getId_materia(),
                materiaprofesor.getHora(),
                materiaprofesor.getDias()
        );

        if (!existingEntries.isEmpty()) {
            return new ResponseEntity<>("Ya existe un registro con los mismos datos.", HttpStatus.CONFLICT);
        }

        materiaprofesorRepository.save(materiaprofesor);
        return new ResponseEntity<>(materiaprofesor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMateriaProfesor(@PathVariable Long id) {
        materiaprofesorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMateriaProfesor(@PathVariable Long id, @RequestBody MateriaProfesor materiaprofesorDetails) {
        MateriaProfesor materiaprofesor = materiaprofesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MateriaProfesor no encontrado con id :" + id));

        // Verifica si ya existe una fila con los mismos valores
        List<MateriaProfesor> existingRows = materiaprofesorRepository.findByIdProfesorAndIdMateriaAndHoraAndDias(
            materiaprofesorDetails.getId_profesor(),
            materiaprofesorDetails.getId_materia(),
            materiaprofesorDetails.getHora(),
            materiaprofesorDetails.getDias()
        );
        if (!existingRows.isEmpty()) {
            // Si existe, devuelve un mensaje de error
            return ResponseEntity.badRequest().body("Ya existe una fila con los mismos valores");
        }

        materiaprofesor.setDias(materiaprofesorDetails.getDias());
        materiaprofesor.setHora(materiaprofesorDetails.getHora());
        // Asegúrate de actualizar todas las demás propiedades que quieras cambiar

        MateriaProfesor updateMateriaProfesor = materiaprofesorRepository.save(materiaprofesor);
        return ResponseEntity.ok(updateMateriaProfesor);
    }

}
