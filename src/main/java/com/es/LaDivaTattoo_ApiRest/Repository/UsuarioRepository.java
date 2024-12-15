package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su nombre. Devuelve un Optional.
    Optional<Usuario> findByNombre(String nombre);

    // Verifica si existe un usuario con el email proporcionado.
    boolean existsByEmail(String email);

    // Verifica si existe un usuario con el número de teléfono proporcionado.
    boolean existsByNumtel(String numTel);
}
