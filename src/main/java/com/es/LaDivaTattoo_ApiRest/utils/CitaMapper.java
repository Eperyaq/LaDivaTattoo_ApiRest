package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.CitaDto;
import com.es.LaDivaTattoo_ApiRest.model.Cita;

/**
 * Clase utilitaria para realizar la conversión entre objetos de tipo {@link Cita} y {@link CitaDto}.
 * Contiene métodos estáticos para mapear entre las entidades y los DTOs correspondientes.
 */
public class CitaMapper {

    /**
     * Convierte un objeto {@link Cita} a un objeto {@link CitaDto}.
     *
     * @param cita El objeto {@link Cita} que se va a convertir.
     * @return El objeto {@link CitaDto} resultante de la conversión.
     */
    public static CitaDto toDto(Cita cita) {
        if (cita == null) {
            return null;
        }
        return new CitaDto(
                cita.getId(),
                cita.getUsuario(),
                cita.getArtista(),
                cita.getFecha(),
                cita.getDescripcion()
        );
    }

    /**
     * Convierte un objeto {@link CitaDto} a un objeto {@link Cita}.
     *
     * @param citaDto El objeto {@link CitaDto} que se va a convertir.
     * @return El objeto {@link Cita} resultante de la conversión.
     */
    public static Cita toEntity(CitaDto citaDto) {
        if (citaDto == null) {
            return null;
        }
        return new Cita(
                citaDto.getId(),
                citaDto.getUsuario(),
                citaDto.getArtista(),
                citaDto.getFecha(),
                citaDto.getDescripcion()
        );
    }
}
