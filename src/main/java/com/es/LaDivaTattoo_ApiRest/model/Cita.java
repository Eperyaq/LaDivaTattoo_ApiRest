package com.es.LaDivaTattoo_ApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    @JsonIgnore //Le pongo esto porque si no se me crea un bucle infinito en la respuesta y me aparece el usuario completo en cada cita
    private Artista artista;

    @Temporal(TemporalType.TIMESTAMP)
   private Date fecha;

   private String descripcion;

    public Cita(long id, Usuario usuario, Artista artista, Date fecha, String descripcion) {
        this.id = id;
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Cita(Usuario usuario, Artista artista, Date fecha, String descripcion) {
        this.usuario = usuario;
        this.artista = artista;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Cita() {}

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
