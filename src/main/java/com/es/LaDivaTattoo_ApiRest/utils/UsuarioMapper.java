package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;

public class UsuarioMapper {

    // Método para convertir de Usuario a UsuarioDto
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

    // Método para convertir de UsuarioDto a Usuario
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
