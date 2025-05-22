package Asistencias.controller;

import Asistencias.model.Asistencia;
import Asistencias.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "http://localhost:4200")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @Autowired
    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    // Registra una asistencia a partir del UID de la tarjeta RFID.

    

    @PostMapping("/registrar-por-uid")
    public ResponseEntity<?> registrarPorUid(@RequestBody Map<String, Integer> payload) {
        String uid = payload.get("uid").toString();
        Integer sesionId = payload.get("sesionId");
        Integer adminId = 1; // por ejemplo, un admin fijo; o traído del JWT

        boolean ok = asistenciaService.registrarPorUidYSesion(uid, sesionId, adminId);
        if (ok) {
            return ResponseEntity.ok(Map.of("mensaje", "Asistencia registrada"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Usuario no encontrado, o ya marcó asistencia"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> obtenerTodasLasAsistencias() {
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        return ResponseEntity.ok(asistencias);
    }

    // Obtener todas las asistencias por sesión
    @GetMapping("/sesion/{sesionId}")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasPorSesion(@PathVariable Integer sesionId) {
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorSesion(sesionId);
        return ResponseEntity.ok(asistencias);
    }

    // Obtener todas las asistencias por alumno
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasPorAlumno(@PathVariable String alumnoId) {
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorAlumno(alumnoId);
        return ResponseEntity.ok(asistencias);
    }

    // Obtener una asistencia específica por sesión y alumno
    @GetMapping("/sesion/{sesionId}/alumno/{alumnoId}")
    public ResponseEntity<Asistencia> obtenerAsistenciaPorSesionYAlumno(@PathVariable Integer sesionId,
            @PathVariable String alumnoId) {
        Asistencia asistencia = asistenciaService.obtenerAsistenciaPorSesionYAlumno(sesionId, alumnoId);
        if (asistencia != null) {
            return ResponseEntity.ok(asistencia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear o actualizar una asistencia
    @PostMapping
    public ResponseEntity<Asistencia> guardarAsistencia(@RequestBody Asistencia asistencia) {
        Asistencia nuevaAsistencia = asistenciaService.guardarAsistencia(asistencia);
        return ResponseEntity.ok(nuevaAsistencia);
    }
}