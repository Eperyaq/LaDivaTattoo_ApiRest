package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Artista;
import com.es.LaDivaTattoo_ApiRest.model.Cita;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CitasRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByUsuario(Usuario usuario);  //Creo esta funcion para poder borrar al usuario

    List<Cita> findByArtista(Artista artista);


    List<Cita> findByFecha(LocalDateTime fecha); // Buscar citas por fecha
}
