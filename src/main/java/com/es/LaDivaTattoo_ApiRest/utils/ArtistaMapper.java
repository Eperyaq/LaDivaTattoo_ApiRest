package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.ArtistaDto;
import com.es.LaDivaTattoo_ApiRest.model.Artista;

public class ArtistaMapper {
    // Método para convertir de Artista a ArtistaDto
    public static ArtistaDto toDto(Artista artista) {
        if (artista == null) {
            return null;
        }
        return new ArtistaDto(
                artista.getId(),
                artista.getNombre(),
                artista.getEspecialidad(),
                artista.getCitas()
        );
    }

    // Método para convertir de ArtistaDto a Artista
    public static Artista toEntity(ArtistaDto artistaDto) {
        if (artistaDto == null) {
            return null;
        }
        return new Artista(
                artistaDto.getId(),
                artistaDto.getNombre(),
                artistaDto.getEspecialidad(),
                artistaDto.getCitas()
        );
    }
}
