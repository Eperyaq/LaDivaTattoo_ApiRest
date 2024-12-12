package com.es.LaDivaTattoo_ApiRest.dto;

import com.es.LaDivaTattoo_ApiRest.model.Cita;


import java.util.List;

public class ArtistaDto {
    private Long id;

    private String nombre;

    private String especialidad;

    private List<Cita> citas;

    public ArtistaDto(Long id, String nombre, String especialidad, List<Cita> citas) {
        this.id = id;
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
