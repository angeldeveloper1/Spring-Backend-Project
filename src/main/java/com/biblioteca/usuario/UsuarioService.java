package com.biblioteca.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Transactional
@Service
public class UsuarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional(readOnly = true)
    public Page<Usuario> findAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
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
}
