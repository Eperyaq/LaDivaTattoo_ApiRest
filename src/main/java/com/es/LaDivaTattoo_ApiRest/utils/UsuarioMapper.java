package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;

/**
 * Clase que proporciona métodos estáticos para convertir entre las entidades
 * {@link Usuario} y {@link UsuarioDto}.
 */
public class UsuarioMapper {

    /**
     * Convierte un objeto {@link Usuario} a su correspondiente DTO {@link UsuarioDto}.
     *
     * @param usuario El objeto {@link Usuario} que se desea convertir.
     * @return Un objeto {@link UsuarioDto} correspondiente al usuario, o null si el usuario es null.
     */
    public static UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getNumtel(),
                usuario.getRoles(),
                usuario.getCitas()
        );
    }

    /**
     * Convierte un objeto {@link UsuarioDto} a su correspondiente entidad {@link Usuario}.
     *
     * @param usuarioDto El objeto {@link UsuarioDto} que se desea convertir.
     * @return Un objeto {@link Usuario} correspondiente al DTO, o null si el DTO es null.
     */
    public static Usuario toEntity(UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }

        return new Usuario(
                usuarioDto.getEmail(),
                usuarioDto.getNombre(),
                usuarioDto.getPassword(),
                usuarioDto.getNumTel(),
                usuarioDto.getRoles(),
                usuarioDto.getCitas()
        );
    }
}
