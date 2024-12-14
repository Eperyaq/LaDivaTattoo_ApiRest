package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;

public class UsuarioRegistrarMapper {
    // Método para convertir de Usuario a UsuarioDto
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

    // Método para convertir de UsuarioDto a Usuario
    public static Usuario toEntity(UsuarioRegistrarDto usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }

        return new Usuario(
                null, // El id será generado automáticamente, así que lo pasamos como null
                usuarioDto.getNombre(),
                usuarioDto.getEmail(),
                usuarioDto.getPassword(),
                usuarioDto.getNumTel(),
                usuarioDto.getRoles(),
                null // Las citas no se pasarán en el registro inicial, ya que el usuario no tiene citas aún
        );
    }
}
