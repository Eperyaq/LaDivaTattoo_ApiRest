package com.es.LaDivaTattoo_ApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Clase que representa un usuario en la aplicación.
 * <p>
 * Esta clase se mapea a una tabla de base de datos llamada "usuarios", y contiene información
 * sobre el usuario, como su nombre, correo electrónico, número de teléfono, roles y citas
 * asociadas.
 * </p>
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del usuario.

    private String nombre;  // Nombre completo del usuario.

    @Column(unique = true)
    private String email;  // Correo electrónico único del usuario.

    private String password;  // Contraseña del usuario.

    @Column(unique = true, name = "numero_telefono")
    private String numtel;  // Número de teléfono del usuario.

    private String roles;  // Roles asignados al usuario (por ejemplo, "ADMIN", "USER").

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore // Evita el bucle infinito al serializar el usuario de cada cita
    private List<Cita> citas;  // Lista de citas asociadas al usuario.

    /**
     * Constructor vacío necesario para JPA.
     */
    public Usuario() {}

    /**
     * Constructor completo para la creación de un usuario con todos los parámetros.
     *
     * @param id El identificador del usuario.
     * @param nombre El nombre completo del usuario.
     * @param email El correo electrónico único del usuario.
     * @param password La contraseña del usuario.
     * @param numtel El número de teléfono del usuario.
     * @param roles Los roles asignados al usuario.
     * @param citas Las citas asociadas al usuario.
     */
    public Usuario(Long id, String nombre, String email, String password, String numtel, String roles, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
        this.roles = roles;
        this.citas = citas;
    }

    /**
     * Constructor para la creación de un usuario sin el identificador (para nuevos usuarios).
     *
     * @param nombre El nombre completo del usuario.
     * @param email El correo electrónico único del usuario.
     * @param password La contraseña del usuario.
     * @param numtel El número de teléfono del usuario.
     * @param roles Los roles asignados al usuario.
     * @param citas Las citas asociadas al usuario.
     */
    public Usuario(String nombre, String email, String password, String numtel, String roles, List<Cita> citas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
        this.roles = roles;
        this.citas = citas;
    }

    // Métodos getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
