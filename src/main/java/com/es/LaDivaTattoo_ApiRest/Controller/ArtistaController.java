package com.es.LaDivaTattoo_ApiRest.Controller;

import com.es.LaDivaTattoo_ApiRest.Service.ArtistaService;
import com.es.LaDivaTattoo_ApiRest.dto.ArtistaDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con los artistas en la API.
 */
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    /**
     * Servicio para gestionar las operaciones relacionadas con los artistas.
     */
    @Autowired
    private ArtistaService service;

    /**
     * Registra un nuevo artista en el sistema.
     *
     * @param artistaDto objeto con los datos del artista a registrar.
     * @return un {@link ResponseEntity} con el artista registrado y el estado HTTP correspondiente.
     * @throws BadRequestException si los datos proporcionados son incorrectos.
     * @throws DuplicateException si el artista ya existe en el sistema.
     */
    @PostMapping("/registrar")
    public ResponseEntity<ArtistaDto> registrarArtista(@RequestBody ArtistaDto artistaDto) {
        try {
            if (artistaDto == null) {
                throw new BadRequestException("Error datos incorrectos");
            }

            ArtistaDto artista = service.registrar(artistaDto);

            return new ResponseEntity<>(artista, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) { // Datos incorrectos o vacíos
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DuplicateException e) { // Artista duplicado
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Obtiene todos los artistas registrados en el sistema.
     *
     * @return un {@link ResponseEntity} con la lista de artistas y el estado HTTP correspondiente.
     */
    @GetMapping("/")
    public ResponseEntity<List<ArtistaDto>> getAll() {
        List<ArtistaDto> artistas = service.getAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    /**
     * Obtiene un artista específico por su identificador.
     *
     * @param id identificador del artista.
     * @return un {@link ResponseEntity} con los datos del artista y el estado HTTP correspondiente.
     * @throws BadRequestException si el identificador proporcionado es incorrecto o está vacío.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDto> getById(@PathVariable String id) {
        if (id.isEmpty() || id == null) {
            throw new BadRequestException("Error datos incorrectos");
        }

        ArtistaDto artistaDto = service.findById(id);

        return new ResponseEntity<>(artistaDto, HttpStatus.OK);
    }

    /**
     * Actualiza los datos de un artista existente.
     *
     * @param cuerpo objeto con los nuevos datos del artista.
     * @param id identificador del artista a actualizar.
     * @return un {@link ResponseEntity} con los datos del artista actualizado y el estado HTTP correspondiente.
     * @throws BadRequestException si los datos proporcionados son incorrectos o están vacíos.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDto> update(@RequestBody ArtistaDto cuerpo, @PathVariable String id) {
        if (cuerpo == null || id == null || id.isEmpty()) {
            throw new BadRequestException("Error, datos incorrectos");
        }

        ArtistaDto artistaDto = service.update(id, cuerpo);

        return new ResponseEntity<>(artistaDto, HttpStatus.OK);
    }

    /**
     * Elimina un artista específico por su identificador.
     *
     * @param id identificador del artista a eliminar.
     * @return un {@link ResponseEntity} con el estado HTTP correspondiente.
     * @throws BadRequestException si el identificador proporcionado es incorrecto o está vacío.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (id.isEmpty()) {
            throw new BadRequestException("Error, datos invalidos");
        }

        ArtistaDto artistaDto = service.delete(id);

        if (artistaDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
