package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Service.TokenService;
import com.es.LaDivaTattoo_ApiRest.Service.UsuarioService;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioLoginDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
import com.es.LaDivaTattoo_ApiRest.error.exception.GenericInternalException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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





}
