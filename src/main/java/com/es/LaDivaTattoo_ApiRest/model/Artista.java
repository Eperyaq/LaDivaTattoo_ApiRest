package com.es.LaDivaTattoo_ApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String nombre;

    private String especialidad;

    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY)
    @JsonIgnore //Le pongo esto porque si no se me crea un bucle infinito en la respuesta y me aparece el artista completo en cada cita
    private List<Cita> citas;

    public Artista(Long id, String password, String nombre, String especialidad, List<Cita> citas) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public Artista(String password, String nombre, String especialidad, List<Cita> citas) {
        this.password = password;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public Artista() {
    }

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
