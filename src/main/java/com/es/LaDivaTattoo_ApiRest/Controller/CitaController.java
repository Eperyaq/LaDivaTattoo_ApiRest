package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Repository.ArtistaRepository;
import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.Service.CitaService;
import com.es.LaDivaTattoo_ApiRest.dto.CitaDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import com.es.LaDivaTattoo_ApiRest.error.exception.UnathorizedException;
import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Cita;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArtistaRepository artistaRepository;



    @PostMapping("/crearCita")
    public ResponseEntity<CitaDto> crearCita(
            @RequestBody CitaDto citaDto
    ){

        try{
            if (citaDto == null){
                throw new BadRequestException("Error, datos incorrectos");
            }
            CitaDto nuevaCita = service.crearCita(citaDto);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CitaDto>> getAll(){
        List<CitaDto> citas = service.getAll();

        return new ResponseEntity<>(citas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CitaDto> getById(
            @PathVariable String id
    ){
        if (id.isEmpty()){
            throw new BadRequestException("Error, datos incorrectos");
        }

        CitaDto citaDto = service.getById(id);

        return new ResponseEntity<>(citaDto, HttpStatus.OK);
    }


    @GetMapping("/usuario/{id}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitasPorUsuario(
            @PathVariable Long id
    ) {
        try {
            // Verificamos si el usuario existe
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));


            List<CitaDto> citas = service.obtenerCitasPorUsuario(usuario);

            return new ResponseEntity<>(citas, HttpStatus.OK);
            // Usuario no encontrado
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/artista/{id}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitasPorArtista(
            @PathVariable Long id, Authentication authentication) {

        try {
            // Verificar si el artista existe
            Artista artista = artistaRepository.findById(id).orElseThrow(() -> new NotFoundException("Artista no encontrado"));

            // Verificar que el artista tenga permisos o sea el mismo que busca
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_ADMIN")))
                    || authentication.getName().equals(artista.getNombre())) {

                // Obtener las citas asociadas al artista
                List<CitaDto> citas = service.obtenerCitasPorArtista(artista);


                return new ResponseEntity<>(citas, HttpStatus.OK);

            } else {
                throw new UnathorizedException("Error, no tienes los permisos para acceder a este endpoint");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> update(
            @PathVariable String id,
            @RequestBody CitaDto citaDTO
    ) {

        if (id.isEmpty() || citaDTO == null) {
            throw new BadRequestException("Error datos incorrectos");
        }

        CitaDto citaActualizada = service.update(id, citaDTO);
        return new ResponseEntity<>(citaActualizada, HttpStatus.OK); // Respuesta exitosa

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable String id,
            Authentication authentication
    ) {

        // Intentar eliminar la cita
        CitaDto cita = service.delete(id);

        if (cita == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Se ha borrado
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
