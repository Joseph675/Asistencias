package Asistencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Materia;
import Asistencias.repository.MateriaEstudianteRepository;
import Asistencias.repository.MateriaRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/materias")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<Materia> getMaterias() {
        return materiaRepository.findAll();
    }

    @GetMapping("/materia/{id_materia}")
    public Materia getMateriaId(@PathVariable Long id_materia) {
        return materiaRepository.findById(id_materia).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> createMateria(@RequestBody Materia materia) {
        // Verificar si la materia ya existe
        Materia existenteMateria = materiaRepository.findByNombreMateria(materia.getNombreMateria());
        if (existenteMateria != null) {
            // Si la materia ya existe, devolver un error
            return new ResponseEntity<>("La materia ya existe en la base de datos", HttpStatus.CONFLICT);

        }

        // Si la materia no existe, guardarla en la base de datos
        materiaRepository.save(materia);
        return ResponseEntity.ok(materia);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteMateria(@PathVariable Long id) {
        // Primero, elimina las filas en estudiantes_materias que hacen referencia a la
        // materia
        materiaestudianteRepository.deleteByIdMateria(id);

        // Luego, elimina la materia
        materiaRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Materia updateMateria(@PathVariable Long id, @RequestBody Materia materiaDetails) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Materia no encontrada con id :" + id));

        // Comprueba si el nombre de la materia ya existe
        Materia existingMateria = materiaRepository.findByNombreMateria(materiaDetails.getNombreMateria());
        if (existingMateria != null && !existingMateria.getIdMateria().equals(id)) {
            throw new IllegalArgumentException("El nombre de la materia ya está en uso");
        }

        materia.setNombreMateria(materiaDetails.getNombreMateria());

        Materia updatedMateria = materiaRepository.save(materia);
        return updatedMateria;
    }

}
