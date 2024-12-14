package com.es.LaDivaTattoo_ApiRest.Service;

import com.es.LaDivaTattoo_ApiRest.Repository.CitasRepository;
import com.es.LaDivaTattoo_ApiRest.Repository.UsuarioRepository;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioDto;
import com.es.LaDivaTattoo_ApiRest.dto.UsuarioRegistrarDto;
import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.DuplicateException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import com.es.LaDivaTattoo_ApiRest.model.Cita;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CitasRepository citasRepository; //Instancia del repositorio de las citas para poder borrar las citas de un usuario antes de borrarlo


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


    public List<UsuarioDto> getAll(){

        List<Usuario> listauser = repository.findAll();

        if (listauser.isEmpty()){
            throw new NotFoundException("No existe ningun usuario en la base de datos");
        }

        List<UsuarioDto> listaDto = new ArrayList<>();

        listauser.forEach(usuario -> listaDto.add(UsuarioMapper.toDto(usuario)));

        return listaDto;
    }

    public UsuarioDto getById(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }


        //Si no encuentra ningun usuario lanza una excepcion
       Usuario user = repository.findById(idL).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        //Si no hay ningun problema devuelve el usuario.
        return UsuarioMapper.toDto(user);


    }


    public UsuarioDto update(UsuarioDto cuerpoCambiado, String id){

        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        ValidarDatos.datosCorrectosUsuario(cuerpoCambiado); //Compruebo que los valores sean correctos y pasen los requisitos

        //busco el usuario para poder actualizarlo si no existe lanza una excepcion
        Usuario user = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, no existe el usuario"));

        user.setNombre(cuerpoCambiado.getNombre());
        user.setPassword(passwordEncoder.encode(cuerpoCambiado.getPassword())); //Hasheo la contraseña nueva

        if (cuerpoCambiado.getCitas() != null && !cuerpoCambiado.getCitas().isEmpty()){ //Si no se han cambiado las citas que se quede con las que tiene
            user.setCitas(cuerpoCambiado.getCitas());
        }

        user.setEmail(cuerpoCambiado.getEmail());
        user.setNumtel(cuerpoCambiado.getNumTel());
        user.setRoles(cuerpoCambiado.getRoles());

        repository.save(user);

        return UsuarioMapper.toDto(user);
    }

    public UsuarioDto delete(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Usuario usuario = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, usuario no encontrado")); //busco al usuario

        List<Cita> citasUsuario = citasRepository.findByUsuario(usuario); //Busco las citas de ese usuario
        citasRepository.deleteAll(citasUsuario); //Borro las citas

        repository.delete(usuario); //Y borro al usuario

        Usuario userBorrado = repository.findById(idL).orElse(null); //Intento buscarlo a ver si se ha borrado correctamente

        if (userBorrado == null) {
            // Si es null, significa que el usuario se ha borrado correctamente
            return null;
        } else {
            // Si no es null, significa que el usuario sigue existiendo
            return UsuarioMapper.toDto(userBorrado);
        }
    }
}
