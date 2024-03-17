package com.biblioteca.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
