package Asistencias.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RfidController {

    private final SimpMessagingTemplate messaging;

    public RfidController(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }

    @PostMapping("/rfid")
    public ResponseEntity<Void> receiveUid(@RequestBody Map<String, String> body) {
        String uid = body.get("uid");
        // Aquí podrías validar contra BD, registrar log, etc.

        // Emitir al frontend por STOMP/WebSocket
        messaging.convertAndSend("/topic/rfid", Collections.singletonMap("uid", uid));
        return ResponseEntity.ok().build();
    }
}
