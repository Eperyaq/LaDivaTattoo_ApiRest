package com.es.LaDivaTattoo_ApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Clase que representa a un artista en la aplicación.
 * <p>
 * Esta clase se mapea a una tabla de base de datos llamada "artistas", que contiene información
 * sobre los artistas, como su nombre, especialidad y las citas que han gestionado. Cada artista
 * puede tener múltiples citas asociadas a él.
 * </p>
 */
@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del artista.

    private String password;  // Contraseña del artista.

    private String nombre;  // Nombre del artista.

    private String especialidad;  // Especialidad del artista, como tatuajes de tipo específico.

    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY)
    @JsonIgnore // Evita el bucle infinito al serializar las citas del artista
    private List<Cita> citas;  // Lista de citas asociadas al artista.

    /**
     * Constructor completo que inicializa todos los atributos del artista.
     *
     * @param id El identificador del artista.
     * @param password La contraseña del artista.
     * @param nombre El nombre del artista.
     * @param especialidad La especialidad del artista.
     * @param citas La lista de citas asociadas al artista.
     */
    public Artista(Long id, String password, String nombre, String especialidad, List<Cita> citas) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    /**
     * Constructor que inicializa el artista sin el id (para la creación de nuevos artistas).
     *
     * @param password La contraseña del artista.
     * @param nombre El nombre del artista.
     * @param especialidad La especialidad del artista.
     * @param citas La lista de citas asociadas al artista.
     */
    public Artista(String password, String nombre, String especialidad, List<Cita> citas) {
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    // Constructor vacío necesario para JPA.
    public Artista() {}

    // Métodos getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
