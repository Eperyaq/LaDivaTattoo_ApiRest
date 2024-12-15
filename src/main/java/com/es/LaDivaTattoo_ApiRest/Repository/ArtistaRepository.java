package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Artista}.
 * Esta interfaz extiende JpaRepository, proporcionando métodos estándar de CRUD (crear, leer, actualizar, eliminar)
 * para la entidad Artista. Spring Data JPA se encarga de la implementación de estos métodos.
 */
@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {}
