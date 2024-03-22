package com.biblioteca.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibroService {
    private LibroRepository libroRepository;
    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    @Transactional(readOnly = true)
    public Page<Libro> findAllLibros(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }
}
