package Asistencias.controller;

import Asistencias.model.Materia;
import Asistencias.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "http://localhost:4200")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Integer id) {
        Optional<Materia> materia = materiaService.getMateriaById(id);
        return materia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.saveMateria(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable Integer id, @RequestBody Materia materiaDetails) {
        Optional<Materia> materiaOptional = materiaService.getMateriaById(id);
        if (materiaOptional.isPresent()) {
            Materia materia = materiaOptional.get();
            materia.setNombre(materiaDetails.getNombre());
            materia.setCodigo(materiaDetails.getCodigo());
            materia.setCreditos(materiaDetails.getCreditos());
            materia.setCarreraPk(materiaDetails.getCarreraPk());
            materia.setCupoMaximo(materiaDetails.getCupoMaximo());
            materia.setAnioCursada(materiaDetails.getAnioCursada());
            materia.setCuatrimestre(materiaDetails.getCuatrimestre());
            materia.setHorasSemanales(materiaDetails.getHorasSemanales());
            materia.setDescripcion(materiaDetails.getDescripcion());
            materia.setActiva(materiaDetails.getActiva());
            return ResponseEntity.ok(materiaService.saveMateria(materia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Integer id) {
        if (materiaService.getMateriaById(id).isPresent()) {
            materiaService.deleteMateria(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}