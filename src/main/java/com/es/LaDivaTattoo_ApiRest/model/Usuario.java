package com.es.LaDivaTattoo_ApiRest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;
    @Column(unique = true, name = "numero_telefono")
    private String numtel;

    private String roles;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Cita> citas;

    public Usuario() {}

    public Usuario(Long id, String nombre, String email, String password, String numtel, String roles, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
        this.roles = roles;
        this.citas = citas;
    }

    public Usuario(String nombre, String email, String password, String numtel, String roles, List<Cita> citas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
        this.roles = roles;
        this.citas = citas;
    }

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
