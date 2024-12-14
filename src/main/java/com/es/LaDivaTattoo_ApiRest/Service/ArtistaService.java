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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CitasRepository citasRepository;

    public ArtistaDto registrar(ArtistaDto artistaDto){
        ValidarDatos.datosCorrectosArtista(artistaDto);

        Artista artista = ArtistaMapper.toEntity(artistaDto); //Creo al artista ya que tengo el cuerpo

        artista.setPassword(passwordEncoder.encode(artistaDto.getPassword())); //Hasheo la contraseña

        repository.save(artista); //Guardo al artista en la bdd

        return artistaDto;

    }

    public List<ArtistaDto> getAll(){

        List<Artista> artistas = repository.findAll();

        if (artistas.isEmpty()){
            throw new NotFoundException("Error, no se ha encontrrado ningun artista");
        }

        List<ArtistaDto> dtos = new ArrayList<>();

        artistas.forEach(artista -> dtos.add(ArtistaMapper.toDto(artista)));

        return dtos;
    }


    public ArtistaDto findById(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado ningun artista"));


        return ArtistaMapper.toDto(artista);
    }

    public ArtistaDto update( String id, ArtistaDto cuerpo){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        ValidarDatos.datosCorrectosArtista(cuerpo);

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("Ningun artista encontrado"));

        artista.setPassword(passwordEncoder.encode(cuerpo.getPassword())); //Hasheamos la contraseña
        artista.setEspecialidad(cuerpo.getEspecialidad());

        if (cuerpo.getCitas() != null && !cuerpo.getCitas().isEmpty()){ //Si no se han cambiado las citas que se quede con las que tiene
            artista.setCitas(cuerpo.getCitas());
        }

        artista.setNombre(cuerpo.getNombre());

        repository.save(artista); //Guardamos cambios

        return ArtistaMapper.toDto(artista);
    }

    public ArtistaDto delete(String id){

        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Artista artista = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, artista no encontrado"));

        List<Cita> citasUsuario = citasRepository.findByArtista(artista); //Busco las citas de ese artista
        citasRepository.deleteAll(citasUsuario); //Borro las citas

        repository.delete(artista); //Y borro al artista

        Artista artistaBorrado = repository.findById(idL).orElse(null);

        if (artistaBorrado == null) {
            // Si es null, significa que el usuario se ha borrado correctamente
            return null;
        } else {
            // Si no es null, significa que el usuario sigue existiendo
            return ArtistaMapper.toDto(artista);
        }
    }

}
