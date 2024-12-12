package com.es.LaDivaTattoo_ApiRest.Repository;

import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
