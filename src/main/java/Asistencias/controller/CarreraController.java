package Asistencias.controller;

import Asistencias.model.Carrera;
import Asistencias.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin(origins = "http://localhost:4200")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    // Obtener todas las carreras
    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        Optional<Carrera> carrera = carreraRepository.findById(id);
        return carrera.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva carrera
    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    // Actualizar una carrera existente
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carreraDetails) {
        Optional<Carrera> carreraOpt = carreraRepository.findById(id);
        if (!carreraOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Carrera carrera = carreraOpt.get();
        carrera.setNombre(carreraDetails.getNombre());
        carrera.setCodigo(carreraDetails.getCodigo());
        carrera.setFacultadId(carreraDetails.getFacultadId());
        carrera.setDuracionanios(carreraDetails.getDuracionanios());
        carrera.setDescripcion(carreraDetails.getDescripcion());
        carrera.setActiva(carreraDetails.getActiva());
        return ResponseEntity.ok(carreraRepository.save(carrera));
    }

    // Eliminar una carrera
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        if (!carreraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carreraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}