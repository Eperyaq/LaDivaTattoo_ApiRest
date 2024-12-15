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

    // Busca todas las citas asociadas a un usuario específico.
    List<Cita> findByUsuario(Usuario usuario);

    // Busca todas las citas asociadas a un artista específico.
    List<Cita> findByArtista(Artista artista);

    // Busca citas que coinciden con una fecha específica.
    List<Cita> findByFecha(LocalDateTime fecha);
}
