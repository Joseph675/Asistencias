package Asistencias.service;

import Asistencias.model.Sesiones;
import Asistencias.repository.SesionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionesService {

    @Autowired
    private SesionesRepository sesionesRepository;

    public List<Sesiones> getAllSesiones() {
        return sesionesRepository.findAll();
    }

    public Optional<Sesiones> getSesionById(Integer id) {
        return sesionesRepository.findById(id);
    }

    public Sesiones saveSesion(Sesiones sesion) {
        return sesionesRepository.save(sesion);
    }

    public List<Object[]> getSesionesGroupedByCurso() {
        return sesionesRepository.findSesionesGroupedByCurso();
    }

    public void deleteSesion(Integer id) {
        sesionesRepository.deleteById(id);
    }
}