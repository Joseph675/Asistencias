package Asistencias.controller;

import Asistencias.model.Carrera_Materia;
import Asistencias.model.Carrera_MateriaId;
import Asistencias.service.Carrera_MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrera_materia")
@CrossOrigin(origins = "http://localhost:4200")
public class Carrera_MateriaController {

    @Autowired
    private Carrera_MateriaService carreraMateriaService;

    @GetMapping
    public List<Carrera_Materia> getAllCarreraMaterias() {
        return carreraMateriaService.getAllCarreraMaterias();
    }

    @GetMapping("/{carreraPk}/{materiaPk}")
    public ResponseEntity<Carrera_Materia> getCarreraMateriaById(@PathVariable Integer carreraPk,
            @PathVariable Integer materiaPk) {
        Carrera_MateriaId id = new Carrera_MateriaId();
        id.setCarreraPk(carreraPk);
        id.setMateriaPk(materiaPk);
        Optional<Carrera_Materia> carreraMateria = carreraMateriaService.getCarreraMateriaById(id);
        return carreraMateria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrera_Materia createCarreraMateria(@RequestBody Carrera_Materia carreraMateria) {
        if (carreraMateria.getId() == null) {
            Carrera_MateriaId id = new Carrera_MateriaId();
            id.setCarreraPk(carreraMateria.getId().getCarreraPk());
            id.setMateriaPk(carreraMateria.getId().getMateriaPk());
            carreraMateria.setId(id);
        }
        return carreraMateriaService.saveCarreraMateria(carreraMateria);
    }

    @PutMapping("/{carreraPk}/{materiaPk}")
    public ResponseEntity<Carrera_Materia> updateCarreraMateria(@PathVariable Integer carreraPk,
            @PathVariable Integer materiaPk, @RequestBody Carrera_Materia carreraMateriaDetails) {
        Carrera_MateriaId id = new Carrera_MateriaId();
        id.setCarreraPk(carreraPk);
        id.setMateriaPk(materiaPk);
        Optional<Carrera_Materia> carreraMateriaOptional = carreraMateriaService.getCarreraMateriaById(id);
        if (carreraMateriaOptional.isPresent()) {
            Carrera_Materia carreraMateria = carreraMateriaOptional.get();
            carreraMateria.setAnioCursada(carreraMateriaDetails.getAnioCursada());
            carreraMateria.setCuatrimestre(carreraMateriaDetails.getCuatrimestre());
            return ResponseEntity.ok(carreraMateriaService.saveCarreraMateria(carreraMateria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{carreraPk}/{materiaPk}")
    public ResponseEntity<Void> deleteCarreraMateria(@PathVariable Integer carreraPk, @PathVariable Integer materiaPk) {
        Carrera_MateriaId id = new Carrera_MateriaId();
        id.setCarreraPk(carreraPk);
        id.setMateriaPk(materiaPk);
        if (carreraMateriaService.getCarreraMateriaById(id).isPresent()) {
            carreraMateriaService.deleteCarreraMateria(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}