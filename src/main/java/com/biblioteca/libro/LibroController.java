package com.biblioteca.libro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/libros")
public class LibroController {
    private LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    @GetMapping
    public Page<Libro> getLibros(@PageableDefault(size = 5, page = 0)Pageable pageable){
        return libroService.findAllLibros(pageable);
    }
    @GetMapping("{idLibro}")
    public Libro getLibro(@PathVariable Long idLibro) {
        return libroService.getLibro(idLibro);
    }

    @PostMapping
    public ResponseEntity<Long> createLibro(@RequestBody Libro libro) {
        Long idLibro = libroService.createLibro(libro);
        return new ResponseEntity<>(idLibro,HttpStatus.CREATED);
    }

    @PutMapping("{idLibro}")
    public Libro updateLibro(@PathVariable Long idLibro, @RequestBody Libro actualizarLibro){
        return libroService.updateLibro(idLibro,actualizarLibro);
    }
    @DeleteMapping("{idLibro}")
    public ResponseEntity<?> deleteLibro(@PathVariable Long idLibro) {
        libroService.deleteLibro(idLibro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
