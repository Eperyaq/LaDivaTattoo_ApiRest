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

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService service;

    @PostMapping("/registrar")
    public ResponseEntity<ArtistaDto> registrarArtista(
            @RequestBody ArtistaDto artistaDto
    ){
        try{
            if (artistaDto == null){
                throw new BadRequestException("Error datos incorrectos");
            }

            ArtistaDto artista = service.registrar(artistaDto);

            return new ResponseEntity<ArtistaDto>(artista, HttpStatus.CREATED);
        }catch (IllegalArgumentException e) { //Datos incorrectos /vacios
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (DuplicateException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<ArtistaDto>> getAll(){
        List<ArtistaDto> artistas = service.getAll();

        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDto> getById(
            @PathVariable String id
    ){

        if (id.isEmpty() || id == null){
            throw new BadRequestException("Error datos incorrectos");
        }

        ArtistaDto artistaDto = service.findById(id);

        return new ResponseEntity<>(artistaDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDto> update(
            @RequestBody ArtistaDto cuerpo,
            @PathVariable String id
    ){
        if (cuerpo == null || id == null || id.isEmpty()){
            throw new BadRequestException("Error, datos incorrectos");
        }

        ArtistaDto artistaDto = service.update(id, cuerpo);

        return new ResponseEntity<>(artistaDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if (id.isEmpty()){
            throw new BadRequestException("Error, datos invalidos");
        }

        ArtistaDto artistaDto = service.delete(id);

        if (artistaDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
