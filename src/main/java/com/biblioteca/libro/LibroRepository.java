package com.biblioteca.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    boolean existsByTituloAndAutor(String titulo, String autor);
}
