package com.es.LaDivaTattoo_ApiRest.Service;

import com.es.LaDivaTattoo_ApiRest.Repository.ArtistaRepository;
import com.es.LaDivaTattoo_ApiRest.Repository.CitasRepository;
import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.dto.ArtistaDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Cita;
import com.es.LaDivaTattoo_ApiRest.utils.ArtistaMapper;
import com.es.LaDivaTattoo_ApiRest.utils.UsuarioMapper;
import com.es.LaDivaTattoo_ApiRest.utils.ValidarDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository repository; //Repositorio para acceder a los artistas

    @Autowired
    private PasswordEncoder passwordEncoder; //Encriptador de contraseñas

    @Autowired
    private CitasRepository citasRepository; //Repositorio para acceder a las citas

    // Registrar un nuevo artista
    public ArtistaDto registrar(ArtistaDto artistaDto){
        ValidarDatos.datosCorrectosArtista(artistaDto); //Validar los datos del artista

        Artista artista = ArtistaMapper.toEntity(artistaDto); //Convertir el DTO a entidad

        artista.setPassword(passwordEncoder.encode(artistaDto.getPassword())); //Encriptar la contraseña

        repository.save(artista); //Guardar al artista en la base de datos

        return artistaDto;
    }

    // Obtener todos los artistas
    public List<ArtistaDto> getAll(){
        List<Artista> artistas = repository.findAll();

        if (artistas.isEmpty()){
            throw new NotFoundException("Error, no se ha encontrado ningun artista");
        }

        List<ArtistaDto> dtos = new ArrayList<>();
        artistas.forEach(artista -> dtos.add(ArtistaMapper.toDto(artista))); //Convertir entidades a DTOs

        return dtos;
    }

    // Buscar un artista por su ID
    public ArtistaDto findById(String id){
        Long idL;

        try {
            idL = Long.parseLong(id); //Convertir el ID a Long
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido"); //Si el ID no es válido
        }

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado ningun artista"));

        return ArtistaMapper.toDto(artista); //Convertir entidad a DTO
    }

    // Actualizar los datos de un artista
    public ArtistaDto update(String id, ArtistaDto cuerpo){
        Long idL;

        try {
            idL = Long.parseLong(id); //Convertir el ID a Long
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido"); //Si el ID no es válido
        }

        ValidarDatos.datosCorrectosArtista(cuerpo); //Validar los datos del artista

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("Ningún artista encontrado"));

        artista.setPassword(passwordEncoder.encode(cuerpo.getPassword())); //Encriptar la nueva contraseña
        artista.setEspecialidad(cuerpo.getEspecialidad()); //Actualizar especialidad

        // Si hay nuevas citas, actualizar las citas
        if (cuerpo.getCitas() != null && !cuerpo.getCitas().isEmpty()){
            artista.setCitas(cuerpo.getCitas());
        }

        artista.setNombre(cuerpo.getNombre()); //Actualizar nombre

        repository.save(artista); //Guardar los cambios en la base de datos

        return ArtistaMapper.toDto(artista); //Convertir entidad a DTO
    }

    // Eliminar un artista
    public ArtistaDto delete(String id){
        Long idL;

        try {
            idL = Long.parseLong(id); //Convertir el ID a Long
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido"); //Si el ID no es válido
        }

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, artista no encontrado"));

        List<Cita> citasUsuario = citasRepository.findByArtista(artista); //Buscar todas las citas del artista
        citasRepository.deleteAll(citasUsuario); //Eliminar todas las citas del artista

        repository.delete(artista); //Eliminar al artista de la base de datos

        Artista artistaBorrado = repository.findById(idL).orElse(null); //Verificar si el artista fue eliminado correctamente

        if (artistaBorrado == null) {
            return null; //El artista fue eliminado
        } else {
            return ArtistaMapper.toDto(artista); //Si el artista sigue existiendo, devolver los datos del artista
        }
    }
}
