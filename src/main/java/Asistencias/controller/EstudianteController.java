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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Estudiante;
import Asistencias.repository.EstudianteRepository;
import Asistencias.repository.MateriaEstudianteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/porEstudiante/{id_estudiante}")
    public List<Estudiante> getMateriaEstudiantePorEstudiante(@PathVariable Long id_estudiante) {
        return estudianteRepository.findByIdEstudiante(id_estudiante);
    }
    

    //Exportar achivo excel
    @GetMapping("/exportExcel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Estudiante> listEstudiantes = estudianteRepository.findAll();

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

    @PostMapping
    public ResponseEntity<?> createEstudiante(@RequestBody Estudiante estudiante) {
        // Buscar si el estudiante ya existe en la base de datos
        Estudiante estudianteExistente = estudianteRepository.findByEmail(estudiante.getEmail());

        // Si el estudiante ya existe, devolver un mensaje de error
        if (estudianteExistente != null) {
            return new ResponseEntity<>("El estudiante ya existe en la base de datos", HttpStatus.CONFLICT);
        }

        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        estudianteRepository.save(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        // Primero, elimina las filas en estudiantes_materias que hacen referencia al estudiante
        materiaestudianteRepository.deleteByIdEstudiante(id);

        // Luego, elimina el estudiante
        estudianteRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id :" + id));

        // Comprueba si el correo electrónico ya existe
        Estudiante existingEstudiante = estudianteRepository.findByEmail(estudianteDetails.getEmail());
        if (existingEstudiante != null && !existingEstudiante.getId_estudiante().equals(id)) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setApellido(estudianteDetails.getApellido());
        estudiante.setEmail(estudianteDetails.getEmail());

        Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
        return updatedEstudiante;
    }

    @PostMapping("/login")
    public Estudiante loginEstudiante(@RequestBody Estudiante estudianteDetails) {
        // Buscar al estudiante en la base de datos
        Estudiante estudiante = estudianteRepository.findByEmail(estudianteDetails.getEmail());

        // Si el estudiante no existe, lanzar un error
        if (estudiante == null) {
            throw new EntityNotFoundException("Estudiante no encontrado con email: " + estudianteDetails.getEmail());
        }

        // Si la password no coincide, lanzar un error
        if (!estudiante.getpassword().equals(estudianteDetails.getpassword())) {
            throw new IllegalArgumentException("password incorrecta");
        }

        // Si las credenciales son correctas, devolver el estudiante
        return estudiante;
    }

}
