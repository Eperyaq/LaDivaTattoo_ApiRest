package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import java.time.LocalDateTime;

/**
 * Clase DTO (Data Transfer Object) para representar los datos de una cita.
 */
public class CitaDto {

    /**
     * Identificador único de la cita.
     */
    private long id;

    /**
     * Usuario asociado a la cita.
     */
    private Usuario usuario;

    /**
     * Artista encargado de la cita.
     */
    private Artista artista;

    /**
     * Fecha y hora programadas para la cita.
     */
    private LocalDateTime fecha;

    /**
     * Descripción de la cita.
     */
    private String descripcion;

    /**
     * Constructor con todos los campos.
     *
     * @param id identificador único de la cita.
     * @param usuario usuario asociado a la cita.
     * @param artista artista encargado de la cita.
     * @param fecha fecha y hora programadas para la cita.
     * @param descripcion descripción de la cita.
     */
    public CitaDto(long id, Usuario usuario, Artista artista, LocalDateTime fecha, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Constructor sin el campo de identificación.
     *
     * @param usuario usuario asociado a la cita.
     * @param artista artista encargado de la cita.
     * @param fecha fecha y hora programadas para la cita.
     * @param descripcion descripción de la cita.
     */
    public CitaDto(Usuario usuario, Artista artista, LocalDateTime fecha, String descripcion) {
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Constructor vacío.
     */
    public CitaDto() {}

    /**
     * Obtiene el identificador único de la cita.
     *
     * @return el identificador de la cita.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la cita.
     *
     * @param id identificador único de la cita.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario asociado a la cita.
     *
     * @return el usuario de la cita.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado a la cita.
     *
     * @param usuario usuario de la cita.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el artista encargado de la cita.
     *
     * @return el artista de la cita.
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     * Establece el artista encargado de la cita.
     *
     * @param artista artista de la cita.
     */
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    /**
     * Obtiene la fecha y hora programadas para la cita.
     *
     * @return la fecha de la cita.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora programadas para la cita.
     *
     * @param fecha fecha de la cita.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la descripción de la cita.
     *
     * @return la descripción de la cita.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la cita.
     *
     * @param descripcion descripción de la cita.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
