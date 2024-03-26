package com.biblioteca.prestamo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PrestamoService {
    private PrestamoRepository prestamoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoService.class);

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }
    @Transactional(readOnly = true)
    public Page<Prestamo> findAllPrestamos(Pageable pageable) {
        return prestamoRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Prestamo getPrestamo(Long id) {
        Prestamo prestamoId = prestamoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No existe el prestamo con ID: " + id));
        return prestamoId;
    }

    public Long createPrestamo(Prestamo prestamo) {
        LOGGER.info("Creating prestamo {} ", prestamo);
        if (prestamo.getUsuario() == null){
            LOGGER.warn("Error, no se ha proporcionado un usuario para el prestamo");
            throw new IllegalArgumentException("No existe un usuario para este prestamo");
        }
        if (prestamo.getLibro() == null){
            LOGGER.warn("Error, no se ha proporcionado un libro para el prestamo");
            throw new IllegalArgumentException("No existe un libro para este prestamo");
        }
        LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
        LocalDate fechaExpiracion = prestamo.getFechaExpiracion();
        if (fechaDevolucion != null && fechaExpiracion != null && fechaExpiracion.isBefore(fechaDevolucion)){
            LOGGER.warn("Error, la fecha de devolucion no puede ser anterior a la fecha de expiracion");
            throw new IllegalArgumentException("La fecha de devolucion no puede ser anterior a la fecha de expiracion");
        }
        Long id = prestamoRepository.save(prestamo).getId();
        LOGGER.info("Prestamo with id {} saved successfully",id);
        return id;
    }
}
