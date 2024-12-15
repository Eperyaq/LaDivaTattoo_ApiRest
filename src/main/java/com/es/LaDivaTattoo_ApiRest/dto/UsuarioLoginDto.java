package com.es.LaDivaTattoo_ApiRest.dto;

/**
 * Clase DTO (Data Transfer Object) para representar los datos de inicio de sesión de un usuario.
 */
public class UsuarioLoginDto {

    /**
     * Nombre del usuario que intenta iniciar sesión.
     */
    private String nombre;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Roles asociados al usuario (e.g., ROLE_USER, ROLE_ADMIN).
     */
    private String roles;

    /**
     * Constructor con todos los campos.
     *
     * @param nombre nombre del usuario.
     * @param password contraseña del usuario.
     * @param roles roles asociados al usuario.
     */
    public UsuarioLoginDto(String nombre, String password, String roles) {
        this.nombre = nombre;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Constructor vacío.
     */
    public UsuarioLoginDto() {}

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene los roles asociados al usuario.
     *
     * @return los roles del usuario.
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Establece los roles asociados al usuario.
     *
     * @param roles roles del usuario.
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
}
