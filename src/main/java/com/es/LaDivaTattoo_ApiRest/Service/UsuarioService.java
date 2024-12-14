package com.es.LaDivaTattoo_ApiRest.Service;

import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
import com.es.LaDivaTattoo_ApiRest.model.Usuario;
import com.es.LaDivaTattoo_ApiRest.utils.UsuarioMapper;
import com.es.LaDivaTattoo_ApiRest.utils.UsuarioRegistrarMapper;
import com.es.LaDivaTattoo_ApiRest.utils.ValidarDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository
                .findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .authorities(authorities) // Usar `authorities` en lugar de `roles`
                .build();

        return userDetails;
    }


    public UsuarioRegistrarDto crearUser(UsuarioRegistrarDto user){

        ValidarDatos.datosCorrectos(user); //valido los datos

        if (repository.existsByEmail(user.getEmail())){
            throw new DuplicateException("EL usuario ya existe (Email)");
        }

        if (repository.existsByNumtel(user.getNumTel())){
            throw new DuplicateException("El usuario ya existe (NumTel)");
        }

        // Creo la entidad Usuario
        Usuario usuario = UsuarioRegistrarMapper.toEntity(user);

        // Hasheo la contraseña
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardo en la base de datos
        repository.save(usuario);

        return user; //Retorno el usuarioDto

    }

}
