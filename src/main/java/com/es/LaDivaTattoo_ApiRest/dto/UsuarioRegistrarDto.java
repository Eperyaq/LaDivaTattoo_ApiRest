package com.es.LaDivaTattoo_ApiRest.dto;

public class UsuarioRegistrarDto {
    private Long id;

    private String nombre;

    private String email;

    private String password;

    private String numTel;

    private String roles;

    public UsuarioRegistrarDto(String nombre, String email, String password, String numTel, String roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
    }

    public UsuarioRegistrarDto(Long id, String nombre, String email, String password, String numTel, String roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.roles = roles;
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
}
