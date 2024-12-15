package com.es.LaDivaTattoo_ApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Clase que representa una cita de un usuario con un artista en la aplicación.
 * <p>
 * Esta clase se mapea a una tabla de base de datos llamada "citas", donde cada cita contiene
 * información sobre el usuario que solicita el servicio, el artista que lo realizará, la fecha
 * de la cita y la descripción del servicio solicitado.
 * </p>
 */
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Identificador único de la cita.

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore // Evita el bucle infinito al serializar el usuario de la cita
    private Usuario usuario;  // Usuario que solicita la cita.

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    @JsonIgnore // Evita el bucle infinito al serializar el artista de la cita
    private Artista artista;  // Artista asignado a la cita.

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;  // Fecha y hora de la cita.

    private String descripcion;  // Descripción del servicio que se solicitará en la cita.

    /**
     * Constructor completo para la creación de una cita con todos los parámetros.
     *
     * @param id El identificador de la cita.
     * @param usuario El usuario que solicita la cita.
     * @param artista El artista que realizará el servicio.
     * @param fecha La fecha y hora de la cita.
     * @param descripcion La descripción del servicio solicitado.
     */
    public Cita(long id, Usuario usuario, Artista artista, LocalDateTime fecha, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Constructor para la creación de una cita sin el identificador (para nuevas citas).
     *
     * @param usuario El usuario que solicita la cita.
     * @param artista El artista que realizará el servicio.
     * @param fecha La fecha y hora de la cita.
     * @param descripcion La descripción del servicio solicitado.
     */
    public Cita(Usuario usuario, Artista artista, LocalDateTime fecha, String descripcion) {
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    // Constructor vacío necesario para JPA.
    public Cita() {}

    // Métodos getter y setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
