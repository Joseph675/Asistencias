package Asistencias.controller;

import Asistencias.model.Facultad;
import Asistencias.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facultades")
@CrossOrigin(origins = "http://localhost:4200")
public class FacultadController {

    @Autowired
    private FacultadRepository facultadRepository;

    // Obtener todas las facultades
    @GetMapping
    public List<Facultad> getAllFacultades() {
        return facultadRepository.findAll();
    }

    // Obtener una facultad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable Long id) {
        Optional<Facultad> facultad = facultadRepository.findById(id);
        return facultad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva facultad
    @PostMapping
    public Facultad createFacultad(@RequestBody Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    // Actualizar una facultad existente
    @PutMapping("/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Long id, @RequestBody Facultad facultadDetails) {
        Optional<Facultad> facultadOpt = facultadRepository.findById(id);
        if (!facultadOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Facultad facultad = facultadOpt.get();
        facultad.setNombre(facultadDetails.getNombre());
        facultad.setDescripcion(facultadDetails.getDescripcion());
        facultad.setActiva(facultadDetails.getActiva());
        return ResponseEntity.ok(facultadRepository.save(facultad));
    }

    // Eliminar una facultad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Long id) {
        if (!facultadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        facultadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
