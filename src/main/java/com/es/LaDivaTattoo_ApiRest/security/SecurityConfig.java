package com.es.LaDivaTattoo_ApiRest.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RsaKeyProperties rsaKeys;

    /**
     * BEAN QUE ESTABLECE EL SECURITY FILTER CHAIN
     * Método que establece una serie de filtros de seguridad para nuestra API
     * ¿Para qué sirve este método?
     * Este método sirve para establecer los filtros de seguridad que las peticiones deberán cumplir antes de
     * llegar al endpoint al que se dirijan
     *
     * request ------> filtro1 -> filtro2 -> filtro3 ... -> endpoint
     *
     * Por así decirlo, al cargar la aplicación, Spring Security coge lo que tengamos aquí definido y lo "pone"
     * delante de nuestra app a modo de filtros de seguridad. Esto lo hace automáticamente.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos "Cross-Site Request Forgery" (CSRF) (No lo trataremos en este ciclo)
                .authorizeHttpRequests(auth -> auth// Filtros para securizar diferentes endpoints de la aplicación

                        // Endpoints públicos
                        .requestMatchers("/usuarios/login", "/usuarios/registrar").permitAll()

                        // Endpoints protegidos (requieren autenticación)
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").authenticated() //Que tenga el mismo nombre que el que busca
                        .requestMatchers(HttpMethod.GET, "/usuarios/").hasRole("ADMIN") //Si no que sea admin
                        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/artistas/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/artistas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/artistas/registrar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/artistas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/artistas/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/citas/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/citas/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/citas/crearCita").authenticated()
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}/citas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/artistas/{id}/citas").authenticated()
                        .requestMatchers(HttpMethod.GET, "/citas").hasRole("ADMIN")

                        .anyRequest().authenticated() // Para el resto de peticiones, el usuario debe estar autenticado
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())) // Establecemos el que el control de autenticación se realice por JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }


    /**
     * Método que carga en el Spring ApplicationContext un Bean de tipo PasswordEncoder
     * ¿Por qué este método? ¿Para qué sirve este método?
     * Este método está aquí para que podamos inyectar donde nos plazca objetos de tipo PasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Método que carga en el Spring ApplicationContext un Bean de tipo AuthenticationManager
     * ¿Por qué este método? ¿Para qué sirve este método?
     * Este método está aquí para que podamos inyectar donde nos plazca objetos de tipo AuthenticationManager
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * JWTDECODER decodifica un token
     * @return
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    /**
     * JWTENCODER codifica un token
     * @return
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }


}
