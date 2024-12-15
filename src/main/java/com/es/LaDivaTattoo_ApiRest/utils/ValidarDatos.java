package com.es.LaDivaTattoo_ApiRest.utils;

import com.es.LaDivaTattoo_ApiRest.dto.ArtistaDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;

/**
 * Clase que proporciona métodos estáticos para validar los datos de los objetos
 * {@link UsuarioRegistrarDto}, {@link UsuarioDto} y {@link ArtistaDto}.
 */
public class ValidarDatos {

    /**
     * Valida los datos de un objeto {@link UsuarioRegistrarDto}.
     * Verifica que los campos obligatorios no sean nulos y que el correo electrónico
     * y la contraseña sigan un formato específico.
     *
     * @param usuarioDto El objeto {@link UsuarioRegistrarDto} que se desea validar.
     * @throws BadRequestException Si los datos son inválidos o faltan datos obligatorios.
     */
    public static void datosCorrectos(UsuarioRegistrarDto usuarioDto){
        // Si no da ninguna excepción está bien y por ende no hace nada

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

    /**
     * Valida los datos de un objeto {@link UsuarioDto}.
     * Verifica que los campos obligatorios no sean nulos y que el correo electrónico
     * y la contraseña sigan un formato específico.
     *
     * @param usuarioDto El objeto {@link UsuarioDto} que se desea validar.
     * @throws BadRequestException Si los datos son inválidos o faltan datos obligatorios.
     */
    public static void datosCorrectosUsuario(UsuarioDto usuarioDto){
        // Si no da ninguna excepción está bien y por ende no hace nada

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

    /**
     * Valida los datos de un objeto {@link ArtistaDto}.
     * Verifica que la contraseña siga un formato específico, con al menos 5 caracteres,
     * una letra mayúscula y un número.
     *
     * @param artistaDto El objeto {@link ArtistaDto} que se desea validar.
     * @throws BadRequestException Si la contraseña no sigue el formato esperado.
     */
    public static void datosCorrectosArtista(ArtistaDto artistaDto){
        // Si no da ninguna excepción está bien y por ende no hace nada

        if (!artistaDto.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{5,}$")) {
            throw new BadRequestException("La contraseña debe tener al menos 5 caracteres, una mayúscula y un número.");
        }
    }
}
