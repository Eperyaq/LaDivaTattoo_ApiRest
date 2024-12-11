package com.es.LaDivaTattoo_ApiRest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    public Cita() {}

    public Cita(long id, Usuario usuario, Artista artista) {
        this.id = id;
        this.usuario = usuario;
        this.artista = artista;
    }

    public Cita(Usuario usuario, Artista artista) {
        this.usuario = usuario;
        this.artista = artista;
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
}
