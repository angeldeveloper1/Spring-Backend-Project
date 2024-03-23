package com.biblioteca.libro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class LibroService {
    private LibroRepository libroRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(LibroService.class);
    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    @Transactional(readOnly = true)
    public Page<Libro> findAllLibros(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Libro getLibro(Long idLibro) {
        Libro libroExiste = libroRepository.findById(idLibro)
                .orElseThrow(() -> new NoSuchElementException("El libro con ID: " + idLibro +" no existe."));
        return libroExiste;
    }
    public Long createLibro(Libro libro) {
        LOGGER.info("Creating book {}", libro);
        boolean bookNamesExists = libroRepository.existsByTituloAndAutor(libro.getTitulo(),libro.getAutor());
        if (bookNamesExists){
            LOGGER.warn("Book with title and author {} already exists", libro);
            throw new IllegalArgumentException("Titulo " + libro.getTitulo() + " autor " + libro.getAutor() + " already exists");
        }
        LOGGER.info("Book {} created", libro);
        Long id = libroRepository.save(libro).getId();
        return id;

    }

    public void deleteLibro(Long idLibro) {
        LOGGER.info("Deleting libro with ID {}", idLibro);
        boolean libroExists = libroRepository.existsById(idLibro);
        if (!libroExists){
            LOGGER.warn("Libro with ID {} doesn't exist",idLibro);
            throw new NoSuchElementException("Libro con ID " + idLibro + " no existe.");
        }
        LOGGER.info("Libro with ID {} deleted.", idLibro);
        libroRepository.deleteById(idLibro);
    }
    @Transactional
    public Libro updateLibro(Long idLibro, Libro actualizarLibro) {
        LOGGER.info("Updating Libro with ID {} ", idLibro);
        Libro libroExistente = libroRepository.findById(idLibro)
                .orElseThrow(() -> new NoSuchElementException("No existe ese libro con ID " +idLibro));
        boolean libroExists = libroRepository.existsByTituloAndAutorAndIdIsNot(actualizarLibro.getTitulo(),actualizarLibro.getAutor(),idLibro);
        if (libroExists) {
            LOGGER.warn("Libro with title and author already exists {}", actualizarLibro);
            throw new IllegalArgumentException("Title " +actualizarLibro.getTitulo() + " and author " +actualizarLibro.getAutor() + " already exist");
        }

        LOGGER.info("Libro {} updated successfully", actualizarLibro);
        libroExistente.setTitulo(actualizarLibro.getTitulo());
        libroExistente.setAutor(actualizarLibro.getAutor());
        return libroExistente;
    }
}
