package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Service.UsuarioService;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioLoginDto;
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

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDto> login(
            @RequestBody UsuarioLoginDto usuarioLoginDto
            ){

        Authentication authentication = null;

        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getNombre(), usuarioLoginDto.getPassword()) //Manera de autenticar el login
            );

            return new ResponseEntity<>(usuarioLoginDto, HttpStatus.OK);

        }catch (Exception e){
            throw new NotFoundException("Credenciales del usuario incorrectas");
        }

    }
}
