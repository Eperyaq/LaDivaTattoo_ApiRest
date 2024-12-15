package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;

/**
 * Clase utilitaria para realizar la conversión entre objetos de tipo {@link Usuario} y {@link UsuarioRegistrarDto}.
 * Contiene métodos estáticos para mapear entre la entidad {@link Usuario} y el DTO {@link UsuarioRegistrarDto}.
 */
public class UsuarioRegistrarMapper {

    /**
     * Convierte un objeto {@link Usuario} a un objeto {@link UsuarioRegistrarDto}.
     *
     * @param usuario El objeto {@link Usuario} que se va a convertir.
     * @return El objeto {@link UsuarioRegistrarDto} resultante de la conversión.
     */
    public static UsuarioRegistrarDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioRegistrarDto(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getPassword(),
                usuario.getNumtel(),
                usuario.getRoles()
        );
    }

    /**
     * Convierte un objeto {@link UsuarioRegistrarDto} a un objeto {@link Usuario}.
     *
     * @param usuarioDto El objeto {@link UsuarioRegistrarDto} que se va a convertir.
     * @return El objeto {@link Usuario} resultante de la conversión.
     */
    public static Usuario toEntity(UsuarioRegistrarDto usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }

        return new Usuario(
                usuarioDto.getNombre(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword(),
                usuarioDto.getNumTel(),
                usuarioDto.getRoles(),
                null // Las citas no se pasarán en el registro inicial, ya que el usuario no tiene citas aún
        );
    }
}
