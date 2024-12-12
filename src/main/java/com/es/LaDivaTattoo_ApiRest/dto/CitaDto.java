package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;

import java.util.Date;

public class CitaDto {

    private long id;

    private Usuario usuario;

    private Artista artista;

    private Date fecha;

    private String descripcion;

    public CitaDto(long id, Usuario usuario, Artista artista, Date fecha, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
