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

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios.
 * Implementa la interfaz UserDetailsService para la autenticación y autorización.
 */
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CitasRepository citasRepository;

    /**
     * Carga un usuario desde el repositorio por su nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return Un objeto UserDetails con la información del usuario.
     * @throws UsernameNotFoundException Si no se encuentra el usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository
                .findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();

        return userDetails;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param user El objeto con los datos del usuario a registrar.
     * @return El DTO del usuario registrado.
     * @throws DuplicateException Si el correo electrónico o número de teléfono ya están registrados.
     */
    public UsuarioRegistrarDto crearUser(UsuarioRegistrarDto user){

        ValidarDatos.datosCorrectos(user);

        if (repository.existsByEmail(user.getEmail())){
            throw new DuplicateException("EL usuario ya existe (Email)");
        }

        if (repository.existsByNumtel(user.getNumTel())){
            throw new DuplicateException("El usuario ya existe (NumTel)");
        }

        Usuario usuario = UsuarioRegistrarMapper.toEntity(user);

        usuario.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(usuario);

        return UsuarioRegistrarMapper.toDto(usuario);
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return Una lista de DTOs de usuarios.
     * @throws NotFoundException Si no hay usuarios en la base de datos.
     */
    public List<UsuarioDto> getAll(){

        List<Usuario> listauser = repository.findAll();

        if (listauser.isEmpty()){
            throw new NotFoundException("No existe ningun usuario en la base de datos");
        }

        List<UsuarioDto> listaDto = new ArrayList<>();

        listauser.forEach(usuario -> listaDto.add(UsuarioMapper.toDto(usuario)));

        return listaDto;
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El DTO del usuario encontrado.
     * @throws BadRequestException Si el identificador no es válido.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    public UsuarioDto getById(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Usuario user = repository.findById(idL).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return UsuarioMapper.toDto(user);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param cuerpoCambiado El DTO con los datos actualizados del usuario.
     * @param id El identificador del usuario a actualizar.
     * @return El DTO del usuario actualizado.
     * @throws BadRequestException Si el identificador no es válido.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    public UsuarioDto update(UsuarioDto cuerpoCambiado, String id){

        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        ValidarDatos.datosCorrectosUsuario(cuerpoCambiado);

        Usuario user = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, no existe el usuario"));

        user.setNombre(cuerpoCambiado.getNombre());
        user.setPassword(passwordEncoder.encode(cuerpoCambiado.getPassword()));

        if (cuerpoCambiado.getCitas() != null && !cuerpoCambiado.getCitas().isEmpty()){
            user.setCitas(cuerpoCambiado.getCitas());
        }

        user.setEmail(cuerpoCambiado.getEmail());
        user.setNumtel(cuerpoCambiado.getNumTel());
        user.setRoles(cuerpoCambiado.getRoles());

        repository.save(user);

        return UsuarioMapper.toDto(user);
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param id El identificador del usuario a eliminar.
     * @return El DTO del usuario eliminado o null si fue eliminado correctamente.
     * @throws BadRequestException Si el identificador no es válido.
     * @throws NotFoundException Si el usuario no se encuentra.
     */
    public UsuarioDto delete(String id){
        Long idL;

        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("id no válido");
        }

        Usuario usuario = repository.findById(idL).orElseThrow(() -> new NotFoundException("Error, usuario no encontrado"));

        List<Cita> citasUsuario = citasRepository.findByUsuario(usuario);
        citasRepository.deleteAll(citasUsuario);

        repository.delete(usuario);

        Usuario userBorrado = repository.findById(idL).orElse(null);

        if (userBorrado == null) {
            return null;
        } else {
            return UsuarioMapper.toDto(userBorrado);
        }
    }
}
