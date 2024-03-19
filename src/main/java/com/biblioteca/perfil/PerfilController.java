package com.biblioteca.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/perfil")
public class PerfilController {

    private PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }
    @GetMapping("/paged")
    public Page<Perfil> getEstudiantes(@PageableDefault(size = 5, page = 0)Pageable pageable){
        return perfilService.getAllEstudiantes(pageable);
    }
    @GetMapping("{id}")
    public Perfil getPerfil(@PathVariable Long id){
        return perfilService.getPerfil(id);
    }
    @PostMapping
    public ResponseEntity<Long> createPerfil(@RequestBody Perfil perfil){
        Long idPerfil = perfilService.createPerfil(perfil);
        return new ResponseEntity<>(idPerfil, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePerfil(@PathVariable Long id) {
        perfilService.deletePerfil(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
