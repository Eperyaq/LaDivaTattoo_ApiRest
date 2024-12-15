package com.es.LaDivaTattoo_ApiRest.dto;

/**
 * Clase DTO (Data Transfer Object) para la transferencia de datos durante el registro de usuarios.
 */
public class UsuarioRegistrarDto {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Número de teléfono del usuario.
     */
    private String numTel;

    /**
     * Roles asignados al usuario (e.g., ROLE_USER, ROLE_ADMIN).
     */
    private String roles;

    /**
     * Constructor con todos los campos excepto el identificador.
     *
     * @param nombre nombre del usuario.
     * @param email correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @param numTel número de teléfono del usuario.
     * @param roles roles asignados al usuario.
     */
    public UsuarioRegistrarDto(String nombre, String email, String password, String numTel, String roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
    }

    /**
     * Constructor con todos los campos, incluido el identificador.
     *
     * @param id identificador único del usuario.
     * @param nombre nombre del usuario.
     * @param email correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @param numTel número de teléfono del usuario.
     * @param roles roles asignados al usuario.
     */
    public UsuarioRegistrarDto(Long id, String nombre, String email, String password, String numTel, String roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
    }

    /**
     * Constructor vacío.
     */
    public UsuarioRegistrarDto() {}

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Obtiene el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Obtiene el número de teléfono del usuario.
     *
     * @return el número de teléfono del usuario.
     */
    public String getNumTel() {
        return numTel;
    }

    /**
     * Establece el número de teléfono del usuario.
     *
     * @param numTel número de teléfono del usuario.
     */
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    /**
     * Obtiene los roles asignados al usuario.
     *
     * @return los roles asignados al usuario.
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Establece los roles asignados al usuario.
     *
     * @param roles roles asignados al usuario.
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
}
