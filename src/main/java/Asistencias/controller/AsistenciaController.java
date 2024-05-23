package Asistencias.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Asistencia;
import Asistencias.repository.AsistenciaRepository;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/asistencias")
@CrossOrigin(origins = "http://localhost:4200")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;
   

    @GetMapping
    public List<Asistencia> getAsistencia() {
        return asistenciaRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<?> createAsistencia(@RequestBody Asistencia asistencia) {
        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        asistenciaRepository.save(asistencia);
        return new ResponseEntity<>(asistencia, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteAsistencia(@PathVariable Long id) {
        asistenciaRepository.deleteById(id);
    }

   

//Exportar achivo excel
    @GetMapping("/exportExcel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Asistencia> listEstudiantes = asistenciaRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Estudiantes");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("Estudiante");
        cell = row.createCell(2); // Nueva celda para Apellido
        cell.setCellValue("Materia");
        cell = row.createCell(3); // Nueva celda para Email
        cell.setCellValue("Fecha");
        cell = row.createCell(4); // Nueva celda para Rol
        cell.setCellValue("Asistio");

        // Agrega más columnas aquí si es necesario
        for (int i = 0; i < listEstudiantes.size(); i++) {
            Asistencia asistencia = listEstudiantes.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(asistencia.getId_estudiante());
            cell = row.createCell(1);
            cell.setCellValue(asistencia.getId_materia());
            cell = row.createCell(2);
            cell.setCellValue(asistencia.getFecha());
            cell = row.createCell(3);
            cell.setCellValue(asistencia.getAsistio());
            
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

        List<Asistencia> listEstudiantes = asistenciaRepository.findAll();
        StringBuilder sb = new StringBuilder();

        sb.append("ID\tEstudiante\tMateria\tFecha\tAsistio\n");
        for (Asistencia asistencia : listEstudiantes) {
            sb.append(asistencia.getId_asistencia()).append("\t")
                    .append(asistencia.getId_estudiante()).append("\t")
                    .append(asistencia.getId_materia()).append("\t")
                    .append(asistencia.getFecha()).append("\t")
                    .append(asistencia.getAsistio()).append("\n");
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

        List<Asistencia> listEstudiantes = asistenciaRepository.findAll();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText("ID Estudiante\tNombre\tApellido\tEmail\tRol\n");
        for (Asistencia Asistencia : listEstudiantes) {
            run.addBreak();
            run.setText(Asistencia.getId_asistencia() + "\t"
                    + Asistencia.getId_estudiante() + "\t"
                    + Asistencia.getId_materia() + "\t"
                    + Asistencia.getFecha() + "\t"
                    + Asistencia.getAsistio());
        }

        document.write(response.getOutputStream());
        return ResponseEntity.ok().build();
    }



   
   
   

    
       

}
