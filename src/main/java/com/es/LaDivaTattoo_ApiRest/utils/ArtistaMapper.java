package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.ArtistaDto;
import com.es.LaDivaTattoo_ApiRest.model.Artista;

/**
 * Clase utilitaria para realizar la conversión entre objetos de tipo {@link Artista} y {@link ArtistaDto}.
 * Contiene métodos estáticos para mapear entre las entidades y los DTOs correspondientes.
 */
public class ArtistaMapper {

    /**
     * Convierte un objeto {@link Artista} a un objeto {@link ArtistaDto}.
     *
     * @param artista El objeto {@link Artista} que se va a convertir.
     * @return El objeto {@link ArtistaDto} resultante de la conversión.
     */
    public static ArtistaDto toDto(Artista artista) {
        if (artista == null) {
            return null;
        }
        return new ArtistaDto(
                artista.getId(),
                artista.getPassword(),
                artista.getNombre(),
                artista.getEspecialidad(),
                artista.getCitas()
        );
    }

    /**
     * Convierte un objeto {@link ArtistaDto} a un objeto {@link Artista}.
     *
     * @param artistaDto El objeto {@link ArtistaDto} que se va a convertir.
     * @return El objeto {@link Artista} resultante de la conversión.
     */
    public static Artista toEntity(ArtistaDto artistaDto) {
        if (artistaDto == null) {
            return null;
        }
        return new Artista(
                artistaDto.getId(),
                artistaDto.getPassword(),
                artistaDto.getNombre(),
                artistaDto.getEspecialidad(),
                artistaDto.getCitas()
        );
    }
}
