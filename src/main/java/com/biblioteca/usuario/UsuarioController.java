package com.biblioteca.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/usuarios")
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

    @PostMapping
    public ResponseEntity<Long> createUsuario(@RequestBody Usuario usuario) {
        Long idUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(idUsuario, HttpStatus.CREATED);
    }
}
