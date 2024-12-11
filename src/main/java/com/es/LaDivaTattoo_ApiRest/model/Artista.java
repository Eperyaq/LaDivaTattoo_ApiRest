package com.es.LaDivaTattoo_ApiRest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especialidad;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Cita> citas;

    public Artista(Long id, String nombre, String especialidad, List<Cita> citas) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.citas = citas;
    }

    public Artista() {}

    public Artista(String nombre, String especialidad, List<Cita> citas) {
        this.nombre = nombre;
        this.especialidad = especialidad;
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
