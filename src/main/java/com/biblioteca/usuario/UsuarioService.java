package com.biblioteca.usuario;

import com.biblioteca.libro.Libro;
import com.biblioteca.libro.LibroRepository;
import com.biblioteca.perfil.Perfil;
import com.biblioteca.perfil.PerfilRepository;
import com.biblioteca.prestamo.Prestamo;
import com.biblioteca.prestamo.PrestamoRepository;
import com.biblioteca.prestamo.PrestamoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Transactional
@Service
public class UsuarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          PerfilRepository perfilRepository,
                          LibroRepository libroRepository,
                          PrestamoRepository prestamoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }
    @Transactional(readOnly = true)
    public Page<Usuario> findAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Usuario getUsuario(Long id) {
        Usuario idExist = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found. ID: " +id));
        return idExist;
    }

    public Long createUsuario(Usuario usuario) {
        LOGGER.info("Creando usuario {} ", usuario);
        if (!checkValidezEmail(usuario.getEmail())){
            LOGGER.warn("Email {} is not valid", usuario.getEmail());
            throw new IllegalArgumentException("Email " +usuario.getEmail() + " not valid");
        }
        boolean emailExists = usuarioRepository.existsByEmail(usuario.getEmail());
        if (emailExists) {
            LOGGER.warn("Email {} already exists.");
            throw new IllegalArgumentException("Email " +usuario.getEmail() + " already exists");
        }
        boolean nameExists = usuarioRepository.existsByName(usuario.getName());
        if (nameExists) {
            LOGGER.warn("Name {} already exists.");
            throw new IllegalArgumentException("Name " +usuario.getName() + " already exists");
        }
        Long id = usuarioRepository.save(usuario).getId();
        return id;
    }

    private boolean checkValidezEmail(String email) {
        return Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
        ).asPredicate().test(email);
    }


    public void deleteUsuario(Long id) {
        LOGGER.info("Deleting User with id {} ", id);
        boolean idExists = usuarioRepository.existsById(id);
        if (!idExists){
            LOGGER.warn("Id {} does not exists.",id);
            throw new NoSuchElementException("User with id: " +id + " does not exist");
        }
        usuarioRepository.deleteById(id);
    }
    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario) {
        LOGGER.info("Updating User with id {} ", id);
        Usuario userExisting = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id " +id +" does not exist."));
        boolean nameExists = usuarioRepository.existsByNameAndIdIsNot(usuario.getName(),id);
        if (nameExists) {
            LOGGER.warn("User with name {} already exist", usuario.getName());
            throw new IllegalArgumentException("Name " +usuario.getName() + " already exist.");
        }
        userExisting.setName(usuario.getName());
        if (!checkValidezEmail(usuario.getEmail())){
            LOGGER.warn("Email {} is not valid", usuario.getEmail());
            throw new IllegalArgumentException("Email " +usuario.getEmail() + " not valid.");
        }
        boolean emailExists = usuarioRepository.existsByEmailAndIdIsNot(usuario.getEmail(), id);
        if (emailExists) {
            LOGGER.warn("User with email {} already exist", usuario.getEmail());
            throw new IllegalArgumentException("Email " +usuario.getEmail() + " already exist");
        }
        userExisting.setEmail(usuario.getEmail());
        if (usuario.getBirthDate() != null) { // Verifica si se proporcionó un nuevo valor de BirthDate
            userExisting.setBirthDate(usuario.getBirthDate());
        }
        userExisting.setPassword(usuario.getPassword());

        LOGGER.info("New user data with id " + id + ": {} ", userExisting);

        return userExisting;

    }

    public Usuario agregarPerfilUsuario(Long idUsuario, Long idPerfil) {
        LOGGER.info("Adding profile to user with id " + idUsuario);
        Usuario userExist = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("User with id " + idUsuario + " does not exist"));
        Perfil profileExist = perfilRepository.findById(idPerfil)
                .orElseThrow(()-> new NoSuchElementException("profile with id " + idPerfil + " does not exist"));
        userExist.setPerfil(profileExist);
        LOGGER.info("New user data with id " + idUsuario + ": {} ", userExist);
        return userExist;
    }

    public Usuario agregarLibroUsuario(Long idUsuario, Long idLibro) {
        LOGGER.info("Adding book to user with id {}", idUsuario);
        Usuario userExist = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("User with id " + idUsuario + " does not exist"));
        Libro libroExist = libroRepository.findById(idLibro)
                .orElseThrow(() -> new NoSuchElementException("User with id " + idLibro + " does not exist"));
        libroExist.setUsuario(userExist);
        return userExist;
    }

    public Usuario agregarPrestamoUsuario(Long idUsuario, Long idPrestamos) {
        LOGGER.warn("Adding prestamos to user with id {}",idUsuario);
        Usuario userExist = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("User with id " + idUsuario + " does not exist"));
        Prestamo prestamoExist = prestamoRepository.findById(idPrestamos)
                .orElseThrow(() -> new NoSuchElementException("User with id " + idPrestamos + " does not exist"));
        prestamoExist.setUsuario(userExist);
        return userExist;
    }
}
