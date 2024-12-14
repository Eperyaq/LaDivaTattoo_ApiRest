package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);

    boolean existsByEmail(String email);
    boolean existsByNumtel(String numTel);
}
