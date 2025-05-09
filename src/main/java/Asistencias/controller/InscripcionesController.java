package Asistencias.controller;

import Asistencias.model.Inscripciones;
import Asistencias.service.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "http://localhost:4200")
public class InscripcionesController {

    @Autowired
    private InscripcionesService inscripcionesService;

    @GetMapping
    public List<Inscripciones> getAllInscripciones() {
        return inscripcionesService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripciones> getInscripcionById(@PathVariable Long id) {
        Optional<Inscripciones> inscripcion = inscripcionesService.getInscripcionById(id);
        return inscripcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inscripciones createInscripcion(@RequestBody Inscripciones inscripcion) {
        return inscripcionesService.saveInscripcion(inscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripciones> updateInscripcion(@PathVariable Long id, @RequestBody Inscripciones inscripcionDetails) {
        Optional<Inscripciones> inscripcionOptional = inscripcionesService.getInscripcionById(id);
        if (inscripcionOptional.isPresent()) {
            Inscripciones inscripcion = inscripcionOptional.get();
            inscripcion.setCursoPk(inscripcionDetails.getCursoPk());
            inscripcion.setAlumnoId(inscripcionDetails.getAlumnoId());
            inscripcion.setFechaInscripcion(inscripcionDetails.getFechaInscripcion());
            inscripcion.setEstado(inscripcionDetails.getEstado());
            return ResponseEntity.ok(inscripcionesService.saveInscripcion(inscripcion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        if (inscripcionesService.getInscripcionById(id).isPresent()) {
            inscripcionesService.deleteInscripcion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
