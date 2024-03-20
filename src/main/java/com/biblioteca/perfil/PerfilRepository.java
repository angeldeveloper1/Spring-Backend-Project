package com.biblioteca.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    boolean existsByTelefono(String telefono);
    boolean existsByTelefonoAndIdIsNot(String telefono, Long id);
}
