package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.Service.TokenService;
import com.es.LaDivaTattoo_ApiRest.Service.UsuarioService;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioLoginDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.*;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import com.es.LaDivaTattoo_ApiRest.utils.UsuarioMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(
            @RequestBody UsuarioLoginDto usuarioLoginDto
    ) {

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getNombre(), usuarioLoginDto.getPassword())// modo de autenticación
            );
        } catch (Exception e) {
            System.out.println("Excepcion en authentication");
            throw new NotFoundException("Credenciales del usuario incorrectas");
        }


        String token = "";
        try {
            token = tokenService.generateToken(authentication);
        } catch (Exception e) {
            System.out.println("Excepcion en generar token");
            throw new GenericInternalException("Error al generar el token de autenticación");
        }

        // Retornamos el token
        return token;

    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRegistrarDto> registrarUser(
            @RequestBody UsuarioRegistrarDto user) {

        try {
            if (user == null){
                throw new BadRequestException("Datos vacios");
            }

            UsuarioRegistrarDto usuarioCreado = service.crearUser(user);

            return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){ //Datos incorrectos /vacios
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (DuplicateException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> getAll(){

        List<UsuarioDto> listauser = service.getAll();

        return new ResponseEntity<>(listauser, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(
            @PathVariable String id, Authentication authentication
    ){
        //Compruebo los datos
        if (id.isEmpty() || id == null){
            throw new BadRequestException("Datos incorrectos");
        }

        //Busco el usuario
        UsuarioDto user = service.getById(id);


        //Comprobar si tiene permisos el usuario autenticado
        if (authentication.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_ADMIN")))
                || authentication.getName().equals(user.getEmail())) {


            // Si tiene permisos, devolver el usuario
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            // Si no tiene permisos, lanzar excepción
            throw new UnathorizedException("No tienes los permisos para acceder al recurso");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(
            @PathVariable String id,
            @RequestBody UsuarioDto cuerpoCambiado
    ){
        if (cuerpoCambiado == null){
            throw new BadRequestException("Error, los datos son incorrectos");
        }

        UsuarioDto userdto = service.update(cuerpoCambiado, id);

        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable String id
    ){
        if (id == null || id.isEmpty()){
            throw new BadRequestException("Datos incorrectos");
        }

        UsuarioDto user = service.delete(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //Borrado correctamente

        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //Ha habido un error interno que no ha podido borrar el usuario
        }
    }


}
