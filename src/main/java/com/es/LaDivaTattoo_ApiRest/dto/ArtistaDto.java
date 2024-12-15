package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Cita;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para representar los datos de un artista.
 */
public class ArtistaDto {

    /**
     * Identificador único del artista.
     */
    private Long id;

    /**
     * Contraseña del artista.
     */
    private String password;

    /**
     * Nombre del artista.
     */
    private String nombre;

    /**
     * Especialidad del artista.
     */
    private String especialidad;

    /**
     * Lista de citas asignadas al artista.
     */
    private List<Cita> citas;

    /**
     * Constructor con todos los campos.
     *
     * @param id identificador único del artista.
     * @param password contraseña del artista.
     * @param nombre nombre del artista.
     * @param especialidad especialidad del artista.
     * @param citas lista de citas asignadas al artista.
     */
    public ArtistaDto(Long id, String password, String nombre, String especialidad, List<Cita> citas) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    /**
     * Constructor sin el campo de identificación.
     *
     * @param password contraseña del artista.
     * @param nombre nombre del artista.
     * @param especialidad especialidad del artista.
     * @param citas lista de citas asignadas al artista.
     */
    public ArtistaDto(String password, String nombre, String especialidad, List<Cita> citas) {
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    /**
     * Constructor vacío.
     */
    public ArtistaDto() {}

    /**
     * Obtiene el identificador único del artista.
     *
     * @return el identificador del artista.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del artista.
     *
     * @param id identificador único del artista.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la contraseña del artista.
     *
     * @return la contraseña del artista.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del artista.
     *
     * @param password contraseña del artista.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre del artista.
     *
     * @return el nombre del artista.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del artista.
     *
     * @param nombre nombre del artista.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la especialidad del artista.
     *
     * @return la especialidad del artista.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del artista.
     *
     * @param especialidad especialidad del artista.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la lista de citas asignadas al artista.
     *
     * @return la lista de citas.
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Establece la lista de citas asignadas al artista.
     *
     * @param citas lista de citas.
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
