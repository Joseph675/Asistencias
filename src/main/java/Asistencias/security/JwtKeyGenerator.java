package Asistencias.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Genera una clave segura para HS512
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Clave secreta segura (Base64): " + base64Key);
    }
}
