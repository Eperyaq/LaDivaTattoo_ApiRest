package com.es.LaDivaTattoo_ApiRest.Service;

import com.es.LaDivaTattoo_ApiRest.Repository.CitasRepository;
import com.es.LaDivaTattoo_ApiRest.dto.CitaDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Cita;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import com.es.LaDivaTattoo_ApiRest.utils.CitaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired
    private CitasRepository repository;

    public CitaDto crearCita(CitaDto citaDto){

        // Verificar que la fecha no sea para ayer
        if (citaDto.getFecha().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("La fecha no puede ser para ayer o una fecha pasada.");
        }

        // Verificar si ya hay una cita en esa fecha
        List<Cita> citas = repository.findByFecha(citaDto.getFecha());
        if (!citas.isEmpty()) {
            throw new DuplicateException("Ya existe una cita en esa fecha y hora.");
        }

        // Crear y guardar la cita
        Cita cita = new Cita();
        cita.setFecha(citaDto.getFecha());
        cita.setDescripcion(citaDto.getDescripcion());
        cita.setUsuario(citaDto.getUsuario());
        cita.setArtista(citaDto.getArtista());

        repository.save(cita);

        // Retornar el DTO de la cita creada
        return CitaMapper.toDto(cita);

    }

    public List<CitaDto> getAll(){

        List<Cita> citas = repository.findAll();

        if (citas.isEmpty()){
            throw new NotFoundException("Error, no se ha encontrado ninguna cita");
        }

        List<CitaDto> listaDto = new ArrayList<>();

        citas.forEach(cita -> listaDto.add(CitaMapper.toDto(cita)));

        return listaDto;
    }


    public CitaDto getById(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        //Buscamos la cita si no la encuentra lanza una excepcion
        Cita cita = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, no se ha encontrado la cita"));

        return CitaMapper.toDto(cita);
    }

    public List<CitaDto> obtenerCitasPorUsuario(Usuario usuario) {

        List<Cita> citas = repository.findByUsuario(usuario);

        List<CitaDto> dtos = new ArrayList<>();

        citas.forEach(cita -> dtos.add(CitaMapper.toDto(cita)));


        return  dtos;
    }



    public List<CitaDto> obtenerCitasPorArtista(Artista artista) {
        List<Cita> citas = repository.findByArtista(artista);

        List<CitaDto> dtos = new ArrayList<>();

        citas.forEach(cita -> dtos.add(CitaMapper.toDto(cita)));

        return  dtos;
    }

    public CitaDto update(String id, CitaDto citaDTO) {

        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        // Verificar si la cita existe
        Cita cita = repository.findById(idL).orElseThrow(() -> new NotFoundException("La cita con ID " + id + " no se encuentra"));

        // Actualizar los campos necesarios

        cita.setFecha(citaDTO.getFecha());

        cita.setDescripcion(citaDTO.getDescripcion());


        // Guardamos los cambios hechos
        Cita citaActualizada = repository.save(cita);

        // Convertir a DTO y devolver
        return CitaMapper.toDto(citaActualizada);
    }




    public CitaDto delete(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Cita cita = repository.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado la cita"));

        repository.delete(cita);

        Cita citaBorrada = repository.findById(idL).orElse(null);

        if (citaBorrada == null){
            return null;
        }else {
            return CitaMapper.toDto(citaBorrada);
        }

    }

}
