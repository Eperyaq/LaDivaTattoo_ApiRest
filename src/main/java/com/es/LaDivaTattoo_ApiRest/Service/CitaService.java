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

    /**
     * Crea una nueva cita.
     *
     * @param citaDto DTO que contiene la información de la cita.
     * @return CitaDto que representa la cita creada.
     * @throws BadRequestException Si la fecha de la cita es pasada.
     * @throws DuplicateException Si ya existe una cita para esa fecha.
     */
    public CitaDto crearCita(CitaDto citaDto){

        // Verificar que la fecha no sea para ayer o una fecha pasada
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

        repository.save(cita); // Guardamos la cita en la base de datos

        // Retornar el DTO de la cita creada
        return CitaMapper.toDto(cita);
    }

    /**
     * Obtiene todas las citas registradas.
     *
     * @return Lista de CitaDto que representan todas las citas.
     * @throws NotFoundException Si no se encuentran citas.
     */
    public List<CitaDto> getAll(){

        List<Cita> citas = repository.findAll();

        if (citas.isEmpty()){
            throw new NotFoundException("Error, no se ha encontrado ninguna cita");
        }

        List<CitaDto> listaDto = new ArrayList<>();

        citas.forEach(cita -> listaDto.add(CitaMapper.toDto(cita)));

        return listaDto;
    }

    /**
     * Obtiene una cita por su ID.
     *
     * @param id ID de la cita.
     * @return CitaDto que representa la cita encontrada.
     * @throws BadRequestException Si el ID no es válido.
     * @throws NotFoundException Si no se encuentra la cita con el ID proporcionado.
     */
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

    /**
     * Obtiene todas las citas asociadas a un usuario.
     *
     * @param usuario Usuario para buscar las citas.
     * @return Lista de CitaDto asociadas al usuario.
     */
    public List<CitaDto> obtenerCitasPorUsuario(Usuario usuario) {

        List<Cita> citas = repository.findByUsuario(usuario);

        List<CitaDto> dtos = new ArrayList<>();

        citas.forEach(cita -> dtos.add(CitaMapper.toDto(cita)));

        return  dtos;
    }

    /**
     * Obtiene todas las citas asociadas a un artista.
     *
     * @param artista Artista para buscar las citas.
     * @return Lista de CitaDto asociadas al artista.
     */
    public List<CitaDto> obtenerCitasPorArtista(Artista artista) {
        List<Cita> citas = repository.findByArtista(artista);

        List<CitaDto> dtos = new ArrayList<>();

        citas.forEach(cita -> dtos.add(CitaMapper.toDto(cita)));

        return  dtos;
    }

    /**
     * Actualiza una cita existente.
     *
     * @param id ID de la cita a actualizar.
     * @param citaDTO DTO con los datos actualizados de la cita.
     * @return CitaDto que representa la cita actualizada.
     * @throws BadRequestException Si el ID no es válido.
     * @throws NotFoundException Si no se encuentra la cita con el ID proporcionado.
     */
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

    /**
     * Elimina una cita por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @return CitaDto que representa la cita eliminada o null si no se encontró.
     * @throws BadRequestException Si el ID no es válido.
     * @throws NotFoundException Si no se encuentra la cita con el ID proporcionado.
     */
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
        } else {
            return CitaMapper.toDto(citaBorrada);
        }
    }

}
