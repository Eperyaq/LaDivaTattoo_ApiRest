package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Cita;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para representar los datos de un usuario.
 */
public class UsuarioDto {

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
     * Roles del usuario (e.g., ROLE_USER, ROLE_ADMIN).
     */
    private String roles;

    /**
     * Lista de citas asociadas al usuario.
     */
    private List<Cita> citas;

    /**
     * Constructor con todos los campos.
     *
     * @param id identificador único del usuario.
     * @param nombre nombre del usuario.
     * @param email correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @param numTel número de teléfono del usuario.
     * @param roles roles del usuario.
     * @param citas lista de citas asociadas al usuario.
     */
    public UsuarioDto(Long id, String nombre, String email, String password, String numTel, String roles, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
        this.citas = citas;
    }

    /**
     * Constructor sin el campo de identificación.
     *
     * @param nombre nombre del usuario.
     * @param email correo electrónico del usuario.
     * @param password contraseña del usuario.
     * @param numTel número de teléfono del usuario.
     * @param roles roles del usuario.
     * @param citas lista de citas asociadas al usuario.
     */
    public UsuarioDto(String nombre, String email, String password, String numTel, String roles, List<Cita> citas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
        this.citas = citas;
    }

    /**
     * Constructor vacío.
     */
    public UsuarioDto() {}

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el identificador del usuario.
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
     * Obtiene los roles del usuario.
     *
     * @return los roles del usuario.
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Establece los roles del usuario.
     *
     * @param roles roles del usuario.
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Obtiene la lista de citas asociadas al usuario.
     *
     * @return la lista de citas del usuario.
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Establece la lista de citas asociadas al usuario.
     *
     * @param citas lista de citas del usuario.
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
