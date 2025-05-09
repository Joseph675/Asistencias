package Asistencias.controller;

import Asistencias.model.Sesiones;
import Asistencias.service.SesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin(origins = "http://localhost:4200")
public class SesionesController {

    @Autowired
    private SesionesService sesionesService;

    @GetMapping
    public List<Sesiones> getAllSesiones() {
        return sesionesService.getAllSesiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesiones> getSesionById(@PathVariable Integer id) {
        Optional<Sesiones> sesion = sesionesService.getSesionById(id);
        return sesion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/agrupadas")
    public List<Object[]> getSesionesAgrupadas() {
        return sesionesService.getSesionesGroupedByCurso();
    }

    @PostMapping
    public Sesiones createSesion(@RequestBody Sesiones sesion) {
        return sesionesService.saveSesion(sesion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sesiones> updateSesion(@PathVariable Integer id, @RequestBody Sesiones sesionDetails) {
        Optional<Sesiones> sesionOptional = sesionesService.getSesionById(id);
        if (sesionOptional.isPresent()) {
            Sesiones sesion = sesionOptional.get();
            sesion.setCursoPk(sesionDetails.getCursoPk());
            sesion.setDia_semana(sesionDetails.getDia_semana());
            sesion.setHoraInicio(sesionDetails.getHoraInicio());
            sesion.setHoraFin(sesionDetails.getHoraFin());
            sesion.setTema(sesionDetails.getTema());
            return ResponseEntity.ok(sesionesService.saveSesion(sesion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable Integer id) {
        if (sesionesService.getSesionById(id).isPresent()) {
            sesionesService.deleteSesion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}