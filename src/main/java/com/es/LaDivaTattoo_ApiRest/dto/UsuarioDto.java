package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Cita;

import java.util.List;

public class UsuarioDto {

    private Long id;

    private String nombre;

    private String email;

    private String password;

    private String numTel;

    private String roles;

    private List<Cita> citas;

    public UsuarioDto(Long id, String nombre, String email, String password, String numTel, String roles, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
        this.citas = citas;
    }

    public UsuarioDto(String nombre, String email, String password, String numTel, String roles, List<Cita> citas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
        this.citas = citas;
    }

    public UsuarioDto() {}

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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
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
