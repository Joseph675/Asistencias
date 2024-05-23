package Asistencias.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.server.ResponseStatusException;

import Asistencias.JwtTokenProvider;
import Asistencias.model.Administrador;
import Asistencias.repository.AdministradorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/administradores")
@CrossOrigin(origins = "http://localhost:4200")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public List<Administrador> getAdministrador() {
        return administradorRepository.findAll();
    }

    //Exportar achivo excel
    @GetMapping("/exportExcel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=estudiantes.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Administrador> listAdministrador = administradorRepository.findAll();

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
        for (int i = 0; i < listAdministrador.size(); i++) {
            Administrador administrador = listAdministrador.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(administrador.getId_administrador());
            cell = row.createCell(1);
            cell.setCellValue(administrador.getNombre());
            cell = row.createCell(2);
            cell.setCellValue(administrador.getApellido());
            cell = row.createCell(3);
            cell.setCellValue(administrador.getEmail());
            cell = row.createCell(4);
            cell.setCellValue(administrador.getrol());
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

        List<Administrador> listAdministrador = administradorRepository.findAll();
        StringBuilder sb = new StringBuilder();

        sb.append("ID Estudiante\tNombre\tApellido\tEmail\tRol\n");
        for (Administrador administrador : listAdministrador) {
            sb.append(administrador.getId_administrador()).append("\t")
                    .append(administrador.getNombre()).append("\t")
                    .append(administrador.getApellido()).append("\t")
                    .append(administrador.getEmail()).append("\t")
                    .append(administrador.getrol()).append("\n");
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

        List<Administrador> listAdministrador = administradorRepository.findAll();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText("ID Estudiante\tNombre\tApellido\tEmail\tRol\n");
        for (Administrador administrador : listAdministrador) {
            run.addBreak();
            run.setText(administrador.getId_administrador() + "\t"
                    + administrador.getNombre() + "\t"
                    + administrador.getApellido() + "\t"
                    + administrador.getEmail() + "\t"
                    + administrador.getrol());
        }

        document.write(response.getOutputStream());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createAdministrador(@RequestBody Administrador administrador) {
        // Buscar si el estudiante ya existe en la base de datos
        Administrador administradorExistente = administradorRepository.findByEmail(administrador.getEmail());

        // Si el estudiante ya existe, devolver un mensaje de error
        if (administradorExistente != null) {
            return new ResponseEntity<>("El estudiante ya existe en la base de datos", HttpStatus.CONFLICT);
        }

        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        administradorRepository.save(administrador);
        return new ResponseEntity<>(administrador, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        // Luego, elimina el estudiante
        administradorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Administrador updateAdministrador(@PathVariable Long id, @RequestBody Administrador administradorDetails) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id :" + id));

        // Comprueba si el correo electrónico ya existe
        Administrador existingAdministrador = administradorRepository.findByEmail(administradorDetails.getEmail());
        if (existingAdministrador != null && !existingAdministrador.getId_administrador().equals(id)) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        administrador.setNombre(administradorDetails.getNombre());
        administrador.setApellido(administradorDetails.getApellido());
        administrador.setEmail(administradorDetails.getEmail());

        Administrador updatedAdministrador = administradorRepository.save(administrador);
        return updatedAdministrador;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Administrador loginRequest) {
        // Buscar el administrador en la base de datos
        Administrador administrador = administradorRepository.findByEmail(loginRequest.getEmail());

        // Comprobar si el administrador existe y la contraseña es correcta
        if (administrador != null && loginRequest.getpassword().equals(administrador.getpassword())) {
            // Inicio de sesión exitoso
            // Generar el token JWT
            String token = jwtTokenProvider.createToken(loginRequest.getEmail(), administrador.getrol());

            // Imprimir el token en la consola
            System.out.println("Token generado: " + token);

            // Crear un mapa para la respuesta
            Map<Object, Object> model = new HashMap<>();
            model.put("id", administrador.getId_administrador());  // Aquí está el cambio
            model.put("nombre", administrador.getNombre());  // Aquí está el cambio
            model.put("email", administrador.getEmail());  // Aquí está el cambio
            model.put("avatar", administrador.getAvatar());  // Aquí está el cambio
            model.put("rol", administrador.getrol());  // Aquí está el cambio
            model.put("token", token);

            // Devolver el token en la respuesta
            return ResponseEntity.ok(model);
        } else {
            // Inicio de sesión fallido
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo electrónico o contraseña incorrectos");
        }
    }

}
