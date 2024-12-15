package com.es.LaDivaTattoo_ApiRest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    /**
     * Servicio encargado de la generación de tokens JWT.
     * Se encarga de construir el header y el payload del token,
     * y de firmarlo utilizando el JwtEncoder.
     */

    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Genera un token JWT para el usuario autenticado.
     *
     * @param authentication Objeto que contiene la información de la autenticación del usuario.
     * @return El token JWT generado.
     */
    public String generateToken(Authentication authentication) {
        // Obtener el instante actual
        Instant now = Instant.now();

        // Obtener los roles del usuario desde sus autoridades (GrantedAuthority)
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Construir el payload (claims) del token
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")  // Emisor del token
                .issuedAt(now)   // Fecha de emisión
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Fecha de expiración (1 hora desde la emisión)
                .subject(authentication.getName()) // El sujeto es el nombre del usuario
                .claim("roles", roles) // Incluir los roles del usuario en el token
                .build();

        // Firmar y codificar el token con el JwtEncoder
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
