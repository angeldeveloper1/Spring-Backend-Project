package com.biblioteca.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/prestamos")
public class PrestamoController {
    private PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public Page<Prestamo> getPrestamos(@PageableDefault(size = 5, page = 0)Pageable pageable) {
        return prestamoService.findAllPrestamos(pageable);
    }
    @GetMapping("{id}")
    public Prestamo getPrestamo(@PathVariable Long id) {
        return prestamoService.getPrestamo(id);
    }
    @PostMapping
    public ResponseEntity<Long> createPrestamo(@RequestBody Prestamo prestamo) {
        Long idPrestamo = prestamoService.createPrestamo(prestamo);
        return new ResponseEntity<>(idPrestamo, HttpStatus.CREATED);
    }

}
