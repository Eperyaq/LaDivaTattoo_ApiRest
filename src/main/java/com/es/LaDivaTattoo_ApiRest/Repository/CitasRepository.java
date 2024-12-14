package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Cita;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CitasRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByUsuario(Usuario usuario);  //Creo esta funcion para poder borrar al usuario
}
