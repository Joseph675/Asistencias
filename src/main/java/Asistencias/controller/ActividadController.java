package Asistencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Actividad;
import Asistencias.repository.ActividadRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/actividades")
@CrossOrigin(origins = "http://localhost:4200")
public class ActividadController {

    @Autowired
    private ActividadRepository actividadRepository;

    

    @GetMapping
    public List<Actividad> getActividad() {
        return actividadRepository.findAll();
    }

    
/*
    //Exportar achivo excel
    @GetMapping("/exportExcel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Estudiante> listEstudiantes = actividadRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Estudiantes");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID Estudiante");
        cell = row.createCell(1);
        cell.setCellValue("Nombre");
        cell = row.createCell(2); // Nueva celda para Apellido
        cell.setCellValue("Apellido");
        cell = row.createCell(3); // Nueva celda para Email
        cell.setCellValue("Email");
        cell = row.createCell(4); // Nueva celda para Rol
        cell.setCellValue("Rol");

        // Agrega más columnas aquí si es necesario
        for (int i = 0; i < listEstudiantes.size(); i++) {
            Estudiante estudiante = listEstudiantes.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(estudiante.getId_estudiante());
            cell = row.createCell(1);
            cell.setCellValue(estudiante.getNombre());
            cell = row.createCell(2);
            cell.setCellValue(estudiante.getApellido());
            cell = row.createCell(3);
            cell.setCellValue(estudiante.getEmail());
            cell = row.createCell(4);
            cell.setCellValue(estudiante.getrol());
            // Agrega más celdas aquí si es necesario
        }

        workbook.write(response.getOutputStream());
        return ResponseEntity.ok().build();
    }

    //Exportar achivo TXT
    @GetMapping("/exportTxt")
    public ResponseEntity<?> exportToTxt(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.txt";
        response.setHeader(headerKey, headerValue);

        List<Estudiante> listEstudiantes = estudianteRepository.findAll();
        StringBuilder sb = new StringBuilder();

        sb.append("ID Estudiante\tNombre\tApellido\tEmail\tRol\n");
        for (Estudiante estudiante : listEstudiantes) {
            sb.append(estudiante.getId_estudiante()).append("\t")
                    .append(estudiante.getNombre()).append("\t")
                    .append(estudiante.getApellido()).append("\t")
                    .append(estudiante.getEmail()).append("\t")
                    .append(estudiante.getrol()).append("\n");
        }

        response.getWriter().write(sb.toString());
        return ResponseEntity.ok().build();
    }

//Exportar achivo word
    @GetMapping("/exportWord")
    public ResponseEntity<?> exportToWord(HttpServletResponse response) throws IOException {
        response.setContentType("application/msword");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.docx";
        response.setHeader(headerKey, headerValue);

        List<Estudiante> listEstudiantes = estudianteRepository.findAll();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText("ID Estudiante\tNombre\tApellido\tEmail\tRol\n");
        for (Estudiante estudiante : listEstudiantes) {
            run.addBreak();
            run.setText(estudiante.getId_estudiante() + "\t"
                    + estudiante.getNombre() + "\t"
                    + estudiante.getApellido() + "\t"
                    + estudiante.getEmail() + "\t"
                    + estudiante.getrol());
        }

        document.write(response.getOutputStream());
        return ResponseEntity.ok().build();
    }

    */
    
    @PostMapping
    public ResponseEntity<?> createActividad(@RequestBody Actividad actividad) {
        // Buscar si el estudiante ya existe en la base de datos
        Actividad actividadExistente = actividadRepository.findByTitulo(actividad.getTitulo());

        // Si el estudiante ya existe, devolver un mensaje de error
        if (actividadExistente != null) {
            return new ResponseEntity<>("El actividad ya existe en la base de datos", HttpStatus.CONFLICT);
        }

        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        actividadRepository.save(actividad);
        return new ResponseEntity<>(actividad, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteActividad(@PathVariable Long id) {

        // Luego, elimina el estudiante
        actividadRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Actividad updateActividad(@PathVariable Long id, @RequestBody Actividad actividadDetails) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id :" + id));


        actividad.setTitulo(actividadDetails.getTitulo());
        actividad.setDetalles_actividad(actividadDetails.getDetalles_actividad());

        Actividad updateActividad = actividadRepository.save(actividad);
        return updateActividad;
    }

   

}
