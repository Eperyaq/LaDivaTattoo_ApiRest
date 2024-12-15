package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Service.TokenService;
import com.es.LaDivaTattoo_ApiRest.Service.UsuarioService;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioLoginDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Autentica a un usuario y genera un token JWT.
     *
     * @param usuarioLoginDto objeto que contiene el nombre y contraseña del usuario.
     * @return el token JWT generado.
     * @throws NotFoundException si las credenciales son incorrectas.
     * @throws GenericInternalException si ocurre un error al generar el token.
     */
    @PostMapping("/login")
    public String login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getNombre(), usuarioLoginDto.getPassword())
            );
        } catch (Exception e) {
            throw new NotFoundException("Credenciales del usuario incorrectas");
        }

        try {
            return tokenService.generateToken(authentication);
        } catch (Exception e) {
            throw new GenericInternalException("Error al generar el token de autenticación");
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user objeto que contiene los datos del usuario a registrar.
     * @return el usuario registrado y el estado HTTP correspondiente.
     * @throws BadRequestException si los datos proporcionados están vacíos.
     * @throws DuplicateException si el usuario ya existe.
     */
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRegistrarDto> registrarUser(@RequestBody UsuarioRegistrarDto user) {
        try {
            if (user == null) {
                throw new BadRequestException("Datos vacíos");
            }

            UsuarioRegistrarDto usuarioCreado = service.crearUser(user);
            return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DuplicateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Obtiene la lista de todos los usuarios registrados.
     *
     * @return la lista de usuarios y el estado HTTP correspondiente.
     */
    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> getAll() {
        List<UsuarioDto> listauser = service.getAll();
        return new ResponseEntity<>(listauser, HttpStatus.OK);
    }

    /**
     * Obtiene un usuario específico por su identificador.
     *
     * @param id identificador del usuario.
     * @param authentication datos de autenticación del usuario que realiza la solicitud.
     * @return los datos del usuario y el estado HTTP correspondiente.
     * @throws BadRequestException si el identificador proporcionado es inválido.
     * @throws UnathorizedException si el usuario no tiene permisos para acceder al recurso.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable String id, Authentication authentication) {
        if (id.isEmpty() || id == null) {
            throw new BadRequestException("Datos incorrectos");
        }

        UsuarioDto user = service.getById(id);

        if (authentication.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_ADMIN")))
                || authentication.getName().equals(user.getEmail())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new UnathorizedException("No tienes los permisos para acceder al recurso");
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id identificador del usuario a actualizar.
     * @param cuerpoCambiado objeto con los nuevos datos del usuario.
     * @return los datos del usuario actualizado y el estado HTTP correspondiente.
     * @throws BadRequestException si los datos proporcionados son incorrectos.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable String id, @RequestBody UsuarioDto cuerpoCambiado) {
        if (cuerpoCambiado == null) {
            throw new BadRequestException("Error, los datos son incorrectos");
        }

        UsuarioDto userdto = service.update(cuerpoCambiado, id);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

    /**
     * Elimina un usuario específico por su identificador.
     *
     * @param id identificador del usuario a eliminar.
     * @return el estado HTTP correspondiente.
     * @throws BadRequestException si el identificador proporcionado es inválido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Datos incorrectos");
        }

        UsuarioDto user = service.delete(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
