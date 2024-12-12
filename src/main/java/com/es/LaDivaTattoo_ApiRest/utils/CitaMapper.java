package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.CitaDto;
import com.es.LaDivaTattoo_ApiRest.model.Cita;

public class CitaMapper {
    // Método para convertir de Cita a CitaDto
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

    // Método para convertir de CitaDto a Cita
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
