package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
