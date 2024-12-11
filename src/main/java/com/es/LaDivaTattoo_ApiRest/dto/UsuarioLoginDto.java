package com.es.LaDivaTattoo_ApiRest.dto;

public class UsuarioLoginDto {

    private String nombre;
    private String password;
    private String roles;

    public UsuarioLoginDto(String nombre, String password, String roles) {
        this.nombre = nombre;
        this.password = password;
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
