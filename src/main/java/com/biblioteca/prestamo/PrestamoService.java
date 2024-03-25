package com.biblioteca.prestamo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoService {
    private PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }
    @Transactional(readOnly = true)
    public Page<Prestamo> findAllPrestamos(Pageable pageable) {
        return prestamoRepository.findAll(pageable);
    }
}
