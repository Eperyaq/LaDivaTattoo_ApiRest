package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;

public class ValidarDatos {
    public static void datosCorrectos(UsuarioRegistrarDto usuarioDto){
        //Si no da ninguna excepción esta bien y por ende no hace nada

        if (usuarioDto.getNombre() == null || usuarioDto.getEmail() == null ||
                usuarioDto.getPassword() == null || usuarioDto.getNumTel() == null) {
            throw new BadRequestException("Datos faltantes o formato inválido.");
        }

        if (!usuarioDto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new BadRequestException("El correo electrónico no es válido.");
        }

        if (!usuarioDto.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{5,}$")) {
            throw new BadRequestException("La contraseña debe tener al menos 5 caracteres, una mayúscula y un número.");
        }

    }

    public static void datosCorrectosUsuario(UsuarioDto usuarioDto){
        //Si no da ninguna excepción esta bien y por ende no hace nada

        if (usuarioDto.getNombre() == null || usuarioDto.getEmail() == null ||
                usuarioDto.getPassword() == null || usuarioDto.getNumTel() == null) {
            throw new BadRequestException("Datos faltantes o formato inválido.");
        }

        if (!usuarioDto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new BadRequestException("El correo electrónico no es válido.");
        }

        if (!usuarioDto.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{5,}$")) {
            throw new BadRequestException("La contraseña debe tener al menos 5 caracteres, una mayúscula y un número.");
        }

    }

}
