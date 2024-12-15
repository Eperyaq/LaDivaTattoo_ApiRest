package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Repository.ArtistaRepository;
import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.Service.CitaService;
import com.es.LaDivaTattoo_ApiRest.dto.CitaDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
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

/**
 * Controlador REST para manejar las operaciones relacionadas con las citas en la API.
 */
@RestController
@RequestMapping("/cita")
public class CitaController {

    /**
     * Servicio para gestionar las operaciones relacionadas con las citas.
     */
    @Autowired
    private CitaService service;

    /**
     * Gestor de autenticación para manejar la seguridad en los endpoints.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Repositorio para manejar las operaciones relacionadas con los usuarios.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Repositorio para manejar las operaciones relacionadas con los artistas.
     */
    @Autowired
    private ArtistaRepository artistaRepository;

    /**
     * Crea una nueva cita basada en los datos proporcionados.
     *
     * @param citaDto objeto con los datos de la cita a crear.
     * @return un {@link ResponseEntity} con la cita creada y el estado HTTP correspondiente.
     */
    @PostMapping("/crearCita")
    public ResponseEntity<CitaDto> crearCita(@RequestBody CitaDto citaDto) {
        try {
            if (citaDto == null) {
                throw new BadRequestException("Error, datos incorrectos");
            }
            CitaDto nuevaCita = service.crearCita(citaDto);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        }catch (DuplicateException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return un {@link ResponseEntity} con la lista de citas y el estado HTTP correspondiente.
     */
    @GetMapping("/")
    public ResponseEntity<List<CitaDto>> getAll() {
        List<CitaDto> citas = service.getAll();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    /**
     * Obtiene una cita específica por su identificador.
     *
     * @param id identificador de la cita.
     * @return un {@link ResponseEntity} con los datos de la cita y el estado HTTP correspondiente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CitaDto> getById(@PathVariable String id) {
        if (id.isEmpty()) {
            throw new BadRequestException("Error, datos incorrectos");
        }
        CitaDto citaDto = service.getById(id);
        return new ResponseEntity<>(citaDto, HttpStatus.OK);
    }

    /**
     * Obtiene todas las citas asociadas a un usuario específico.
     *
     * @param id identificador del usuario.
     * @return un {@link ResponseEntity} con la lista de citas y el estado HTTP correspondiente.
     */
    @GetMapping("/usuario/{id}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitasPorUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            List<CitaDto> citas = service.obtenerCitasPorUsuario(usuario);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todas las citas asociadas a un artista específico.
     *
     * @param id identificador del artista.
     * @param authentication información de autenticación del usuario actual.
     * @return un {@link ResponseEntity} con la lista de citas y el estado HTTP correspondiente.
     */
    @GetMapping("/artista/{id}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitasPorArtista(@PathVariable Long id, Authentication authentication) {
        try {
            Artista artista = artistaRepository.findById(id).orElseThrow(() -> new NotFoundException("Artista no encontrado"));
            if (authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.equals(new SimpleGrantedAuthority("ROLE_ADMIN")))
                    || authentication.getName().equals(artista.getNombre())) {
                List<CitaDto> citas = service.obtenerCitasPorArtista(artista);
                return new ResponseEntity<>(citas, HttpStatus.OK);
            } else {
                throw new UnathorizedException("Error, no tienes los permisos para acceder a este endpoint");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param id identificador de la cita a actualizar.
     * @param citaDTO objeto con los nuevos datos de la cita.
     * @return un {@link ResponseEntity} con los datos de la cita actualizada y el estado HTTP correspondiente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CitaDto> update(@PathVariable String id, @RequestBody CitaDto citaDTO) {
        if (id.isEmpty() || citaDTO == null) {
            throw new BadRequestException("Error datos incorrectos");
        }
        CitaDto citaActualizada = service.update(id, citaDTO);
        return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
    }

    /**
     * Elimina una cita específica por su identificador.
     *
     * @param id identificador de la cita a eliminar.
     * @param authentication información de autenticación del usuario actual.
     * @return un {@link ResponseEntity} con el estado HTTP correspondiente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, Authentication authentication) {
        CitaDto cita = service.delete(id);
        if (cita == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
