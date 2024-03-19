package com.biblioteca.perfil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Service
public class PerfilService {
    private PerfilRepository perfilRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PerfilService.class);

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Transactional(readOnly = true)
    public Page<Perfil> getAllEstudiantes(Pageable pageable) {
        return perfilRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Perfil getPerfil(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such element with id " +id));
    }

    public Long createPerfil(Perfil perfil) {
        LOGGER.info("Creating Profile {} ", perfil);
        if (perfil.getName() == null) {
            LOGGER.warn("Error, el nombre no puede ser nulo", perfil.getName());
            throw new IllegalArgumentException("Error, name cannot be null.");
        }

        boolean telefonoExist = perfilRepository.existsByTelefono(perfil.getTelefono());
        if (telefonoExist) {
            LOGGER.warn("Telefono {} ya existe ", perfil.getTelefono());
            throw new IllegalArgumentException("Telefono " + perfil.getTelefono() + " already exist.");
        }
        if (perfil.puntosPremios<0){
            LOGGER.warn("Error, los premios no pueden ser menor que CERO (0)",perfil.puntosPremios);
            throw new IllegalArgumentException("Error, reward points cannot be less than zero");
        }
        Long id = perfilRepository.save(perfil).getId();
        LOGGER.info("Profile with ID {} was saved successfully.", id);
        return id;
    }

    public void deletePerfil(Long idPerfil) {
        LOGGER.info("Deleting Perfil with ID {}",idPerfil);
        boolean idExists = perfilRepository.existsById(idPerfil);
        if (!idExists){
            LOGGER.warn("Id {} does not exists.",idPerfil);
            throw new NoSuchElementException("Perfil with id: " +idPerfil + " does not exist");
        }
        perfilRepository.deleteById(idPerfil);
    }
}
