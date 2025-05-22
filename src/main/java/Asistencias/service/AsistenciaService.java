package Asistencias.service;

import Asistencias.model.Asistencia;
import Asistencias.model.Usuario;
import Asistencias.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Asistencias.repository.UsuarioRepository;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final UsuarioRepository usuarioRepo;

    @Autowired
    public AsistenciaService(AsistenciaRepository asistenciaRepo,
            UsuarioRepository usuarioRepo) {
        this.asistenciaRepository = asistenciaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Asistencia> obtenerTodasLasAsistencias() {
    return asistenciaRepository.findAll();
}
    public List<Asistencia> obtenerAsistenciasPorSesion(Integer sesionId) {
        return asistenciaRepository.findBySesionId(sesionId);
    }

    public List<Asistencia> obtenerAsistenciasPorAlumno(String alumnoId) {
        return asistenciaRepository.findByAlumnoId(alumnoId);
    }

    public Asistencia obtenerAsistenciaPorSesionYAlumno(Integer sesionId, String alumnoId) {
        return asistenciaRepository.findBySesionIdAndAlumnoId(sesionId, alumnoId);
    }

    public Asistencia guardarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    /**
     * Registra una asistencia a partir del UID y la sesión.
     * 
     * @param uid           El UID de la tarjeta RFID.
     * @param sesionId      El ID de la sesión (sesiones.sesionId).
     * @param registradoPor El idUsuUni de quien registra (puede ser admin).
     * @return true si se creó, false si usuario no existe o ya tenía asistencia.
     */
    public boolean registrarPorUidYSesion(String uid, Integer sesionId, Integer registradoPor) {
        // 1) Buscar usuario por UID
        Optional<Usuario> optUser = usuarioRepo.findByUid(uid);
        if (optUser.isEmpty())
            return false;
        Usuario alumno = optUser.get();

        // 2) Verificar que no exista asistencia previa para esa sesión y alumno
        boolean ya = asistenciaRepository.existsBySesionIdAndAlumnoId(sesionId, String.valueOf(alumno.getIdUsuUni()));
        if (ya)
            return false;

        // 3) Crear y guardar asistencia
        Asistencia a = new Asistencia();
        a.setSesionId(sesionId);
        a.setAlumnoId(String.valueOf(alumno.getIdUsuUni()));
        a.setPresente(true);
        a.setJustificada(false);
        a.setObservaciones("");
        a.setRegistradoPor(registradoPor);
        a.setFechaRegistro(LocalDateTime.now());

        asistenciaRepository.save(a);
        return true;
    }
}