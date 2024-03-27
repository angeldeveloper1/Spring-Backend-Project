package com.biblioteca.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/usuarios")
@PreAuthorize("hasAnyRole('ADMIN', 'BIBL')")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/paged")
    public Page<Usuario> getUsuarios(@PageableDefault(size = 5, page = 0)Pageable pageable){
        return usuarioService.findAllUsuarios(pageable);
    }
    @GetMapping("{id}")
    public Usuario getUsuario(@PathVariable Long id){
        return usuarioService.getUsuario(id);
    }

    @PostMapping
    public ResponseEntity<Long> createUsuario(@RequestBody Usuario usuario) {
        Long idUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(idUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @PutMapping("{idUsuario}/perfil/{idPerfil}")
    public Usuario agregarPerfilUsuario(@PathVariable Long idUsuario, @PathVariable Long idPerfil){
        return usuarioService.agregarPerfilUsuario(idUsuario, idPerfil);
    }
    @PutMapping("{idUsuario}/libros/{idLibro}")
    public Usuario agregarLibroUsuario(@PathVariable Long idUsuario, @PathVariable Long idLibro){
        return usuarioService.agregarLibroUsuario(idUsuario,idLibro);
    }
    @PutMapping("{idUsuario}/prestamos/{idPrestamos}")
    public Usuario agregarPrestamoUsuario(@PathVariable Long idUsuario, @PathVariable Long idPrestamos){
        return usuarioService.agregarPrestamoUsuario(idUsuario,idPrestamos);
    }
}

